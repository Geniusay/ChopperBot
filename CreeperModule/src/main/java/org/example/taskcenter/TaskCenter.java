package org.example.taskcenter;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.example.cache.FileCache;
import org.example.cache.FileCacheManagerInstance;
import org.example.config.CreeperConfigFile;
import org.example.config.CreeperLogConfigFile;
import org.example.config.TaskCenterConfig;
import org.example.constpool.PluginName;
import org.example.exception.FileCacheException;
import org.example.log.ChopperLogFactory;
import org.example.log.LoggerType;
import org.example.taskcenter.handler.BootStrapTaskHandler;
import org.example.taskcenter.request.ReptileRequest;
import org.example.taskcenter.task.ReptileTask;
import org.example.thread.ChopperBotGuardianTask;
import org.example.util.ConfigFileUtil;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Genius
 * @date 2023/07/28 21:57
 **/

/**
 * 爬虫任务中心包含了以下功能
 * 1，请求分发和包装爬虫任务
 * 2，记录正在运行的爬虫任务
 * 3，未完成的爬虫任务修复
 */
public class TaskCenter implements ChopperBotGuardianTask{

    private long waitingQueueTime;  //等待队列时间

    private int threads;            //处理任务的线程数量

    private volatile static TaskCenter taskCenter;

    private Map<String, ReptileTask> runningTask;   //正在运行的任务

    private BlockingQueue<ReptileTask> waitingTask; //正在等待运行的任务

    private ExecutorService taskPool;               //任务池子

    private BootStrapTaskHandler bootStrapTaskHandler; // 请求处理封装类

    private FileCache creeperLogFileCache; //爬虫日志文件

    private ReentrantLock lock = new ReentrantLock();


    public TaskCenter() {
    }

    private TaskCenter(int waitingQueueTime, int threads, int capacity){
        this.runningTask = new ConcurrentHashMap<>();
        this.waitingTask = new ArrayBlockingQueue<>(capacity);
        this.bootStrapTaskHandler = new BootStrapTaskHandler();
        this.taskPool = Executors.newFixedThreadPool(threads);
        this.waitingQueueTime = waitingQueueTime;
        this.threads = threads;
    }

    public static TaskCenter center(){
        if(taskCenter==null){
            synchronized (TaskCenter.class){
                if(taskCenter==null){
                    if (!init()) {
                        return null;
                    }
                }
            }
        }
        return taskCenter;
    }

    private static boolean init(){
        FileCache configFileCache = FileCacheManagerInstance.getInstance().getFileCache(CreeperConfigFile.getFullFilePath());
        Object obj = configFileCache.get("taskCenter");
        if (obj!=null) {
            TaskCenterConfig taskCenterConfig = JSONObject.parseObject(obj.toString(), TaskCenterConfig.class);
            taskCenter = new TaskCenter(
                    taskCenterConfig.getWaitingTime(),
                    taskCenterConfig.getThreads(),
                    taskCenterConfig.getQueueCapacity()
            );
            return taskCenter.newLogFile(new CreeperLogConfigFile(new ArrayList<>()));
        }
        return false;
    }

    public boolean addTask(ReptileTask task){
        try {
            if(runningTask.containsKey(task.getTaskId())){
                return false;
            }
            return waitingTask.offer(task,waitingQueueTime,TimeUnit.MILLISECONDS);
        }catch (InterruptedException e){
            //TODO 待完善错误处理
            return false;
        }
    }

    public void work(){
        ChopperLogFactory.getLogger(LoggerType.Creeper)
                .info("TaskCenter start to work.threads:{},waitingTime:{}s",threads,waitingQueueTime/1000);
        while(true){
            try {
                ReptileTask task = waitingTask.take();
                runningTask.put(task.getTaskId(),task);
                taskPool.submit(task::reptile);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean finishTask(String taskId){
        ReptileTask reptileTask;
        if ((reptileTask=runningTask.remove(taskId))!=null) {
            try {
                //查看是不是需要更新日志
                try {
                    lock.lock();
                    if (CreeperLogConfigFile.needNewDayLog(creeperLogFileCache.getFileName())) {
                        updateLogFile();
                    }
                    creeperLogFileCache.append(reptileTask,"task","-1");
                }finally {
                    lock.unlock();
                }
            }catch (FileCacheException | InterruptedException e){
                return false;
            }
        }
        return false;
    }

    /**
     * 如果时间变为新的一天则创建新的日志文件，并清理缓存中的日志文件
     */
    private void updateLogFile(){
        CreeperLogConfigFile configFile = new CreeperLogConfigFile(new ArrayList<>());
        String oldFilePath = creeperLogFileCache.getFullFilePath();
        if (newLogFile(configFile)) {
            if(!oldFilePath.equals(creeperLogFileCache.getFullFilePath())){
                FileCacheManagerInstance.getInstance().deleteFileCache(oldFilePath);
            }
        }
    }

    public boolean newLogFile(CreeperLogConfigFile configFile){
        if (ConfigFileUtil.createConfigFile(CreeperLogConfigFile.getFullFilePath(),configFile)) {
            creeperLogFileCache = FileCacheManagerInstance.getInstance().getFileCache(CreeperLogConfigFile.getFullFilePath());
            return true;
        }
        return false;
    }

    /**
     * 恢复当日因异常关闭而没有完成的任务
     */
    public void restoreTaskCenter(){
        ChopperLogFactory.getLogger(LoggerType.Creeper).
                info("<{}> start to restore...", PluginName.TASK_CENTER_PLUGIN);
        JSONArray tasks = (JSONArray)creeperLogFileCache.get("task");
        int restoreNum = 0;
        if(tasks.size()>0){
            for (Object task : tasks) {
                if(task instanceof JSONObject){
                    ReptileTask reptileTask = JSONObject.parseObject(task.toString(), ReptileTask.class);
                    if(reptileTask.getType().equals(ReptileTask.TaskStatus.Running)){
                        restoreNum++;
                        try {
                            waitingTask.put(reptileTask);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }
        ChopperLogFactory.getLogger(LoggerType.Creeper).info("<{}> Find {} reptile task need restore,already insert waiting queue",
                PluginName.TASK_CENTER_PLUGIN,restoreNum);
    }

    public boolean shutdown(){
        taskPool.shutdown();
        return taskPool.isShutdown();
    }

    public void request(ReptileRequest request){
        ReptileTask task = bootStrapTaskHandler.distribute(request);
        if(task!=null){
            addTask(task);
        }
    }

    @Override
    public void threadTask() {
        this.work();
    }

}
