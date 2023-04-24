package org.example.cache;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.example.common.ConfigFile;
import org.example.exception.FileCacheException;
import org.example.util.JsonFileUtil;
import org.example.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Genius
 * @date 2023/04/24 00:01
 **/

//TODO 文件缓存写入优化 考虑是否要加一个缓存写入Buffer，将短时间内多个相同Key的内容存入Buffer中，合并存入
public class FileCache <T extends ConfigFile>{
    private T configFile;                                       //文件配置类，得到文件信息和文件夹结构，更新文件存入时间
    private Logger logger;

    private ConcurrentHashMap<String,Object> jsonFile;          //文件内容缓存

    private ConcurrentHashMap<String,Object> oldJsonFileTemp;   //保存上一个版本的文件内容，用于优化自动刷入
    private static final int MAX_WRITE_BUFFER_LIMIT = 4096;    //最大写入缓存上线

    private AtomicInteger writeByte;        //当前写入的字节数

    private BlockingQueue<ConcurrentHashMap<String,Object>> syncChannel; //磁盘刷入阻塞队列

    //TODO 优化 考虑是否采用一个定时线程管理所有FileCache的写入
    private ExecutorService pool; //Sync线程池

    private long autoSyncTime; //自动刷入时间

    public FileCache(T configFile) throws FileCacheException {
        init(configFile,10);
    }

    public FileCache(T configFile,long autoSyncTime)throws FileCacheException {
        init(configFile,autoSyncTime);
    }

    /**
     * 初始化方法
     * @param configFile 配置文件
     * @param autoSyncTime 自动刷入时间
     * @throws FileCacheException
     */
    private void init(T configFile, long autoSyncTime) throws FileCacheException {
        this.configFile = configFile;
        this.configFile.updateConfigTime();//更新一下当前的时间

        this.logger = LoggerFactory.getLogger("FileCache:"+this.configFile.getFileName());

        this.autoSyncTime = autoSyncTime;
        if(!load(getFileName())){
            throw new FileCacheException("FileCache Init Error,please Check if your path is correct");
        }

        this.writeByte = new AtomicInteger(0);
        this.syncChannel = new ArrayBlockingQueue<>(20);
        this.pool = Executors.newSingleThreadExecutor();
        pool.submit(new SyncMan());
    }

    /**
     * 加载文件内容
     * @return boolean
     */
    private boolean load(String path){
        Map<String, Object> stringObjectMap = JsonFileUtil.readJsonFile(path);
        if(Objects.isNull(stringObjectMap)){
           logger.error("{}配置文件不存在!",path);
           return false;
        }

        this.jsonFile = new ConcurrentHashMap<>(stringObjectMap);
        this.oldJsonFileTemp = new ConcurrentHashMap<>(stringObjectMap);
        return true;
    }

    /**
     * 重新加载内存池Map
     * @return boolean
     */
    public synchronized boolean reload(){
        return load(Paths.get(this.configFile.getFilePath(),this.configFile.getFileName()).toString());
    }

    //写入
    public int write(String key,Object data) throws InterruptedException {
        String jsonDataStr = JSON.toJSONString(data);
        int writeBytes = key.getBytes().length + jsonDataStr.getBytes().length;

        JSONObject jsonData = writeInData(key, data);
        jsonFile.put("data",jsonData);

        ConcurrentHashMap<String,Object> temp = new ConcurrentHashMap<>(jsonFile);
        int newBytes = writeByte.updateAndGet(x -> x + writeBytes >= MAX_WRITE_BUFFER_LIMIT ? 0 : x + writeBytes);

        //TODO 此处会发生脏读问题，即put进入的Map版本不是当前版本，但是目前没有发现该问题是否会影响到文件写入
        if(newBytes==0){
            logger.debug("缓冲区已满，刷入磁盘");
            syncChannel.put(temp);
        }
        return writeBytes;
    }

    /**
     * 追加内容
     * @param key   key
     * @param append 追加内容
     * @return
     * @throws InterruptedException
     */
    public int append(String key,Object append) throws InterruptedException {
        Object data = getData(key);
        StringBuffer buffer = new StringBuffer(JSON.toJSONString(data));
        String jsonStr = buffer.append(JSON.toJSONString(append)).toString();
        return write(key, jsonStr);
    }

    private JSONObject writeInData(String key,Object value){
        JSONObject data = JSONObject.parseObject(jsonFile.get("data").toString()); //此处必须返回一个新的JsonObject，否则会导致旧版本同步更新
        data.put(key,value);
        return data;
    }

    /**
     * 获取文件内容
     * @param key
     * @return
     */
    public Object get(String key){
        return jsonFile.get(key);
    }

    /**
     * 获取文件数据内容
     * @param key
     * @return
     */
    public Object getData(String key){
        Object data = this.get("data");
        JSONObject jsonObject = JSONObject.parseObject(data.toString());
        return jsonObject.get(key);
    }

    /**
     * 清除已写入的字节数记录
     */
    protected void clearWriteBytes(){
        writeByte.updateAndGet(x->0);
    }

    /**
     * 判断当前时间是否超过更新时间
     * @return boolean
     */
    public boolean needAutoSync(){
        long now = TimeUtil.getCurrentSecond();
        return now- TimeUtil.getSecond(configFile.getUpdateTime())>autoSyncTime;
    }

    /**
     * 强制刷入磁盘
     */
    public void forceSync(){
        clearWriteBytes();
        ConcurrentHashMap<String,Object> temp = new ConcurrentHashMap<>(jsonFile);
        try {
            syncChannel.put(temp);
        } catch (InterruptedException e) {
            logger.debug("自动刷入失败");
        }
    }

    /**
     * 超过缓冲区刷入
     * @return
     */
    private boolean sync(ConcurrentHashMap<String,Object> take){
        configFile.updateConfigTime(); //刷新配置文件刷入时间
        String dir = getFileName();
        if(oldJsonFileTemp.get("data").toString().equals(take.get("data").toString())){
            logger.debug("未发生版本变化");
            return true;
        }

        configFile.onlyUpdateTime(take);
        oldJsonFileTemp = new ConcurrentHashMap<>(take);
        File file = JsonFileUtil.writeJsonFile(dir, take);
        logger.debug("正在写入{}新版本",dir);
        return Objects.isNull(file);
    }

    public BlockingQueue getFileChannel(){
        return this.syncChannel;
    }

    public String getFileName(){
        return Paths.get(this.configFile.getFilePath(), this.configFile.getFileName()).toString();
    }

    public long getSyncTime(){
        return this.autoSyncTime;
    }

    class SyncMan implements Runnable{

        @Override
        public void run() {
            for(;;){
                try {
                    ConcurrentHashMap<String, Object> take = syncChannel.take();
                    sync(take);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}