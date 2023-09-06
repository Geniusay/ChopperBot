package org.example.cache;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.example.bean.ConfigFile;
import org.example.bean.FileType;
import org.example.exception.FileCacheException;
import org.example.util.JsonFileUtil;
import org.example.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Paths;
import java.util.*;
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

    private static int MAX_WRITE_BUFFER_LIMIT = 4096;    //最大写入缓存上线

    private AtomicInteger writeByte;        //当前写入的字节数

    private BlockingQueue<ConcurrentHashMap<String,Object>> syncChannel; //磁盘刷入阻塞队列

    //TODO 优化 考虑是否采用一个定时线程管理所有FileCache的写入
    private ExecutorService pool; //Sync线程池

    private long autoSyncTime; //自动刷入时间

    public FileCache(T configFile) throws FileCacheException {
        init(configFile,10);
    }

    /**
     * 构造方法
     * @param configFile        指定的配置文件
     * @param autoSyncTime      自动刷新时间
     * @param maxWriteBufferLimit   写入上限
     * @throws FileCacheException
     */
    public FileCache(T configFile,long autoSyncTime,int maxWriteBufferLimit)throws FileCacheException {
        MAX_WRITE_BUFFER_LIMIT = maxWriteBufferLimit;
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
        if(!load(getFullFilePath())){
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
        return true;
    }

    /**
     * 重新加载内存池Map
     * @return boolean
     */
    public synchronized boolean reload(){
        return load(Paths.get(this.configFile.getFilePath(),this.configFile.getFileName()).toString());
    }

    /**
     * 递进寻找JsonObject中的对象，并改写
     * @param data
     * @param keys
     * @return
     * @throws InterruptedException
     */
    public int writeKeys(Object data,String...keys) throws InterruptedException, FileCacheException {
       return writeKeys(false,data,keys);
    }

    private int writeKeys(boolean isAppend,Object data,String...keys) throws FileCacheException, InterruptedException {

        if(Objects.isNull(data)){return 0;}

        String jsonDataStr = JSON.toJSONString(data);
        int writeBytes = jsonDataStr.getBytes().length;
        if(writeBytes==0){return 0;}

        Object jsonData = writeInData(isAppend,data,keys);
        if(Objects.isNull(jsonData)){return 0;}

        ConcurrentHashMap<String,Object> temp = new ConcurrentHashMap<>(JSONObject.parseObject(JSON.toJSONString(jsonFile),Map.class));
        int newBytes = writeByte.updateAndGet(x -> x + writeBytes >= MAX_WRITE_BUFFER_LIMIT ? 0 : x + writeBytes);

        //TODO 此处会发生脏读问题，即put进入的Map版本不是当前版本，但是目前没有发现该问题是否会影响到文件写入
        if(newBytes==0){
            logger.debug("缓冲区已满，刷入磁盘");
            syncChannel.put(temp);
        }
        return writeBytes;
    }

    //写入
    public int write(Object data,String key) throws InterruptedException, FileCacheException {
       return this.writeKeys(data,key);
    }

    private Object writeInData(boolean isAppend,Object value,String...keys) throws FileCacheException {
        String[] finds = Arrays.copyOf(keys, keys.length - 1);
        Object data = this.get("data");
        Object temp = this.get(finds);
        if(temp instanceof JSONArray){
            try{
                //元素添加
                int index = Integer.parseInt(keys[keys.length-1]);
                if(index==-1){
                    ((JSONArray) temp).add(value);
                }else{
                    String oldValue = ((JSONArray) temp).get(index).toString();
                    value = isAppend?oldValue+value.toString():value;
                    if(oldValue.equals(value)){
                        return null;
                    }
                    ((JSONArray) temp).set(index,value);
                }
            }catch (Exception e){
                return null;
            }
        }
        else if(temp instanceof JSONObject){
            String key = keys[keys.length-1];
            String oldValue = ((JSONObject) temp).get(key).toString();
            value = isAppend?oldValue+value:value;
            if(oldValue.equals(value)){
                return null;
            }
            ((JSONObject) temp).put(key,value);
        }
        else{
            throw new FileCacheException("the keys is error!");
        }
        return data;
    }

    /**
     * 追加内容，支持数组添加内容，添加内容，需要将最后一个key置为-1
     * @param keys  要查找的key
     * @param append 追加内容
     * @return
     * @throws InterruptedException
     */
    public int append(Object append,String...keys) throws InterruptedException, FileCacheException {
        return writeKeys(true,append,keys);
    }


    public void writeSyncFlag(){
        writeByte.updateAndGet(x -> x + 1);
    }

    /**
     * 根据key数组，不断向下获取内容
     * @param keys
     * @return
     */
    public Object get(String...keys){
        Object jsonObject = this.get("data");
        for (String key : keys) {
            if(jsonObject instanceof JSONObject){
                jsonObject = ((JSONObject) jsonObject).get(key);
            }
            else if(jsonObject instanceof JSONArray){
                jsonObject = ((JSONArray) jsonObject).get(Integer.parseInt(key));
            }else{
                return jsonObject;
            }
        }
        return jsonObject;
    }

    /**
     * 获取文件内容
     * @param key
     * @return
     */
    private Object get(String key){
        return jsonFile.get(key);
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
        return now - TimeUtil.getSecond(configFile.getUpdateTime())>autoSyncTime;
    }

    /**
     * 强制刷入磁盘
     */
    public void forceSync(){
        if(writeByte.get()==0){
            logger.debug("未发生版本变化");
            return;
        }
        clearWriteBytes();
        ConcurrentHashMap<String,Object> temp = new ConcurrentHashMap<>(JSONObject.parseObject(JSON.toJSONString(jsonFile),Map.class));
        try {
            syncChannel.put(temp);
        } catch (InterruptedException e) {
            logger.error("自动刷入失败,Error:{}",e.getMessage());
        }
    }

    /**
     * 缓冲区刷入
     * @return
     */
    private boolean sync(ConcurrentHashMap<String,Object> take){
        configFile.updateConfigTime(); //刷新配置文件刷入时间
        String dir = getFullFilePath();
        configFile.onlyUpdateTime(take);
        File file = JsonFileUtil.writeJsonFile(dir, take);
        return Objects.isNull(file);
    }




    public BlockingQueue getFileChannel(){
        return this.syncChannel;
    }

    public String getFullFilePath(){
        return Paths.get(this.configFile.getFilePath(), this.configFile.getFileName()).toString();
    }

    public String getFilePath(){
        return this.configFile.getFilePath();
    }

    public String getFileName(){
        return this.configFile.getFileName();
    }

    public long getSyncTime(){
        return this.autoSyncTime;
    }

    public FileType getFileType(){
        return this.configFile.getFileType();
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

    @Override
    public int hashCode() {
        return Objects.hash(configFile);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof FileCache){
            if(((FileCache) obj).getFullFilePath().equals(this.getFullFilePath())){
                return true;
            }else if(obj.hashCode() == this.hashCode()){
                return true;
            }
        }
        return false;
    }

    public boolean close(){
        pool.shutdown();
        return pool.isShutdown();
    }
}
