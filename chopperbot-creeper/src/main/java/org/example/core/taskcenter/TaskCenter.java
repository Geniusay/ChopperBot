package org.example.core.taskcenter;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.example.cache.FileCache;
import org.example.cache.FileCacheManagerInstance;
import org.example.config.CreeperConfigFile;
import org.example.config.CreeperLogConfigFile;
import org.example.config.TaskCenterConfig;
import org.example.constpool.PluginName;
import org.example.core.loadtask.LoadTask;
import org.example.core.manager.CreeperBuilder;
import org.example.core.manager.CreeperGroupCenter;
import org.example.core.manager.CreeperManager;
import org.example.core.manager.Creeper;
import org.example.core.taskcenter.task.TaskRecord;
import org.example.core.taskcenter.task.TaskStatus;
import org.example.core.taskmonitor.MonitorCenter;
import org.example.core.taskmonitor.TaskMonitor;
import org.example.exception.FileCacheException;
import org.example.init.InitPluginRegister;
import org.example.log.ChopperLogFactory;
import org.example.log.LoggerType;
import org.example.plugin.GuardPlugin;
import org.example.core.taskcenter.request.ReptileRequest;
import org.example.core.taskcenter.task.ReptileTask;
import org.example.plugin.PluginCheckAndDo;
import org.example.core.loadconfig.LoadConfig;
import org.example.util.ConfigFileUtil;
import org.example.util.TimeUtil;
import org.springframework.util.StringUtils;

import java.util.*;
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
@Data
public class TaskCenter extends GuardPlugin {

    private long waitingQueueTime;  //等待队列时间

    private int threads;            //处理任务的线程数量

    private Map<String, ReptileTask> runningTask;   //正在运行的任务

    private Map<String,Future> runningTaskFuture;

    private BlockingQueue<ReptileTask> waitingTask; //正在等待运行的任务

    private Map<String,TaskRecord> recordMap;


    private ExecutorService taskPool;               //任务池子

    private FileCache creeperLogFileCache; //爬虫日志文件

    private ReentrantLock lock = new ReentrantLock();


    public TaskCenter(String module, String pluginName, List<String> needPlugins, boolean isAutoStart) {
        super(module, pluginName, needPlugins, isAutoStart);
        afterDo = false;
    }

    @Override
    public boolean init(){
        FileCache configFileCache = FileCacheManagerInstance.getInstance().getFileCache(CreeperConfigFile.getFullFilePath());
        Object obj = configFileCache.get("taskCenter");
        if (obj!=null) {
            TaskCenterConfig taskCenterConfig = JSONObject.parseObject(obj.toString(), TaskCenterConfig.class);
            this.threads =  taskCenterConfig.getThreads();
            this.runningTask = new ConcurrentHashMap<>();
            this.runningTaskFuture = new ConcurrentHashMap<>();
            this.waitingTask = new ArrayBlockingQueue<>(taskCenterConfig.getQueueCapacity());
            this.taskPool = Executors.newFixedThreadPool(threads);
            this.waitingQueueTime =  taskCenterConfig.getWaitingTime();
            this.recordMap = new ConcurrentHashMap<>();
            if(newLogFile(new CreeperLogConfigFile(new ArrayList<>()))){
                restoreTaskCenter();
                return super.init();
            }
        }
        return false;
    }

    @Override
    public void start() {
        try {
            ReptileTask task = waitingTask.poll(waitingQueueTime,TimeUnit.MILLISECONDS);
            if(task!=null){
                if(runningTask.containsKey(task.getTaskId())) return;
                try {
                    lock.lock();
                    if(runningTask.containsKey(task.getTaskId())) return;
                    runningTask.put(task.getTaskId(),task);
                    changeTaskType(task, TaskStatus.Running);
                    checkAndUpdateLogFile();

                    creeperLogFileCache.writeSyncFlag();
                } finally {
                   lock.unlock();
                }

                PluginCheckAndDo.CheckAndDo((plugin)->{
                    ((MonitorCenter)plugin).register(task.getTaskId(), task.getLoadTask());
                },PluginName.TASK_MONITOR_PLUGIN);

                runningTaskFuture.put(task.getTaskId(),taskPool.submit(task::reptile));
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean addTask(ReptileTask task){
        if(runningTask.containsKey(task.getTaskId())) return false;
        try {
            lock.lock();
                if(runningTask.containsKey(task.getTaskId())) return false;
                if (waitingTask.offer(task,waitingQueueTime, TimeUnit.MILLISECONDS)) {
                    TaskRecord taskRecord = new TaskRecord(task);
                    recordMap.put(task.getTaskId(),taskRecord);
                    try {
                        creeperLogFileCache.append(taskRecord,"task","-1");
                    } catch (FileCacheException e) {
                        return false;
                    }
                    changeTaskType(task, TaskStatus.Already);
                    checkAndUpdateLogFile();
                }else{
                    return false;
                }
        }catch (Exception e){
            //TODO 待完善错误处理
            ChopperLogFactory.getLogger(LoggerType.Creeper).error("[TaskCenter] Error {} cant serializable",task.getTaskId());
            return false;
        }finally {
            lock.unlock();
        }
        return true;
    }

    public boolean finishTask(String taskId){
        ReptileTask reptileTask;
        if ((runningTaskFuture.remove(taskId)!=null)&
                (reptileTask=runningTask.remove(taskId))!=null) {
            changeTaskType(reptileTask, TaskStatus.Finish);
            try {
                //查看是不是需要更新日志
                lock.lock();
                checkAndUpdateLogFile();
                creeperLogFileCache.writeSyncFlag();
            }catch (Exception e){
                return false;
            }finally {
                PluginCheckAndDo.CheckAndDo((plugin)->{
                    ((MonitorCenter)plugin).close(reptileTask.getTaskId());
                },PluginName.TASK_MONITOR_PLUGIN);
                lock.unlock();
            }
        }
        return false;
    }

    public boolean stopTask(String taskId){
        Future future;
        if((future=runningTaskFuture.remove(taskId))!=null){
            ReptileTask reptileTask = runningTask.remove(taskId);
            reptileTask.end();
            future.cancel(true);
            changeTaskType(reptileTask,TaskStatus.Finish);
            reptileTask.setEndTime(TimeUtil.getNowTime_YMDHMS());
            recordMap.get(taskId).setEndTime(reptileTask.getEndTime());
            try {
                lock.lock();
                checkAndUpdateLogFile();
                creeperLogFileCache.writeSyncFlag();
            }catch (Exception e){
                return false;
            }finally {
                lock.unlock();
            }
        }
        this.info(String.format("%s stop success!", taskId));
        return true;
    }

    /**
     * 检测同时更新日志
     */
    private void checkAndUpdateLogFile(){
        if (CreeperLogConfigFile.needNewDayLog(creeperLogFileCache.getFileName())) {
            updateLogFile();
        }
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
                waitingTask.forEach(
                        v->{
                            try {
                            creeperLogFileCache.append(v,"task","-1");
                            } catch (Exception e) {
                                //TODO 需要有对应方法
                            }
                        }
                );
                runningTask.forEach(
                        (k,v) -> {
                            try {
                                creeperLogFileCache.append(v,"task","-1");
                            } catch (Exception e) {
                                //TODO 需要有对应方法
                            }
                        }
                );
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
    public boolean restoreTaskCenter(){
        ChopperLogFactory.getLogger(LoggerType.Creeper).
                info("<{}> start to restore...", PluginName.TASK_CENTER_PLUGIN);
        int restoreNum = 0;
        JSONArray restoreTask = (JSONArray) creeperLogFileCache.get("task");
        if(restoreTask.size()>0){
            for (int i=0;i<restoreTask.size();i++) {
                Object task = restoreTask.get(i);
                if(task instanceof JSONObject){
                    TaskRecord taskRecord = JSONObject.parseObject(task.toString(), TaskRecord.class);
                    ReptileTask reptileTask = taskRecord.getReptileTask();
                    recordMap.put(taskRecord.getTaskId(),taskRecord);
                    if(taskRecord.getType().equals(TaskStatus.Running)
                            ||taskRecord.getType().equals(TaskStatus.Already)){
                        restoreNum++;
                        try {
                            changeTaskType(reptileTask, TaskStatus.Already);
                            creeperLogFileCache.writeKeys(taskRecord,"task",String.valueOf(i));
                            waitingTask.put(reptileTask);
                        } catch (InterruptedException |FileCacheException e) {
                           throw new RuntimeException(e);
                        }
                    }
                }
            }
        }
        ChopperLogFactory.getLogger(LoggerType.Creeper).info("<{}> Find {} reptile task need restore,already insert waiting queue",
                PluginName.TASK_CENTER_PLUGIN,restoreNum);
        return true;
    }


    private void changeTaskType(ReptileTask task,TaskStatus status){
        if(task.getType()!= TaskStatus.No_Status){
            task.setType(status);
            recordMap.get(task.getTaskId()).setType(status);
        }
    }

    public void request(ReptileRequest request){
        ReptileTask task = getReptileTask(request);
        if(task!=null){
            addTask(task);
        }else{
            this.error(String.format("There are no suitable creeper for this %s group", request.getCreeperGroup()));
        }
    }

    private ReptileTask getReptileTask(ReptileRequest request){
        String creeperName = request.getCreeperName();
        String creeperGroup = request.getCreeperGroup();
        Object param = request.getParam();
        Class<? extends LoadConfig> fcc;

        if(StringUtils.isEmpty(creeperName)){
            fcc = CreeperGroupCenter.getFirstConfig(creeperGroup);
        }else{
            fcc = CreeperGroupCenter.getLoadConfig(creeperGroup,creeperName);
        }
        if(fcc!=null){
            LoadConfig loadConfig = CreeperBuilder.buildLoadConfig(fcc, param);
            if(loadConfig!=null){
                CreeperManager plugin = InitPluginRegister.getPlugin(PluginName.CREEPER_MANAGER_PLUGIN, CreeperManager.class);
                assert plugin != null;
                LoadTask loadTask = plugin.getLoadTask(loadConfig);
                if(loadTask!=null){
                    return new ReptileTask(loadTask,request,loadConfig.getTaskId());
                }
            }
        }
        return null;
    }
    @Override
    public void shutdown(){
        super.shutdown();
        taskPool.shutdown();
    }

}
