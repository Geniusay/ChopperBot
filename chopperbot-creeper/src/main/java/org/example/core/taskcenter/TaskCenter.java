package org.example.core.taskcenter;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.example.cache.FileCache;
import org.example.cache.FileCacheManagerInstance;
import org.example.config.CreeperConfigFile;
import org.example.config.TaskCenterConfig;
import org.example.constpool.PluginName;
import org.example.core.loadtask.LoadTask;
import org.example.core.manager.CreeperBuilder;
import org.example.core.manager.CreeperGroupCenter;
import org.example.core.manager.CreeperManager;
import org.example.core.taskcenter.observer.AbstractTaskCenterObserver;
import org.example.core.taskcenter.task.TaskStatus;
import org.example.init.InitPluginRegister;
import org.example.core.taskcenter.request.ReptileRequest;
import org.example.core.taskcenter.task.ReptileTask;
import org.example.core.loadconfig.LoadConfig;
import org.example.plugin.SpringGuardPlugin;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.*;

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
@Component
public class TaskCenter extends SpringGuardPlugin {

    private long waitingQueueTime;  //等待队列时间

    private int threads;            //处理任务的线程数量

    private Map<String, ReptileTask> runningTask;   //正在运行的任务

    private Map<String,Future> runningTaskFuture;

    private BlockingQueue<ReptileTask> waitingTask; //正在等待运行的任务

    private ExecutorService taskPool;               //任务池子

    private TaskCenterLogger taskCenterLogger;

    @Resource
    private List<AbstractTaskCenterObserver> taskObservers;

    public TaskCenter() {
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
            this.taskCenterLogger = new TaskCenterLogger(waitingTask,runningTask);
            return super.init();
        }
        return false;
    }

    @Override
    public void start() {
        try {
            ReptileTask task = waitingTask.poll(waitingQueueTime,TimeUnit.MILLISECONDS);
            if(task!=null){
                if(runningTask.putIfAbsent(task.getTaskId(),task)!=null) return;
                taskCenterLogger.syncLog();
                runningTaskFuture.put(task.getTaskId(),taskPool.submit(task::reptile));
                changeTaskType(task, TaskStatus.Running);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean addTask(ReptileTask task){
        if(runningTask.containsKey(task.getTaskId())) return false;
        try {
            TaskLog log = new TaskLog(
                    task.getTaskId(),
                    task.getStartTime(),
                    task.getEndTime(),
                    task.getType()
            );
            taskCenterLogger.appendLog(log);
            if (waitingTask.offer(task,waitingQueueTime, TimeUnit.MILLISECONDS)) {
                changeTaskType(task, TaskStatus.Already);
            }else{
                return false;
            }
        }catch (Exception e){
            this.error("爬虫任务队列已满!");
            return false;
        }
        return true;
    }

    public boolean finishTask(String taskId){
        ReptileTask reptileTask;
        if ((runningTaskFuture.remove(taskId)!=null)&
                (reptileTask=runningTask.remove(taskId))!=null) {
            changeTaskType(reptileTask, TaskStatus.Finish);
            taskCenterLogger.syncLog();
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
            taskCenterLogger.syncLog();
        }
        this.info(String.format("%s stop success!", taskId));
        return true;
    }

    private void changeTaskType(ReptileTask task,TaskStatus status){
        task.setType(status);
        noticeObserver(task,status);
        TaskLog taskLog = taskCenterLogger.getTaskLogMap().get(task.getTaskId());
        if(taskLog!=null){
            taskLog.setType(status);
        }
    }

    private void noticeObserver(ReptileTask task,TaskStatus status){
        for (AbstractTaskCenterObserver taskObserver : taskObservers) {
            taskObserver.listen(status,task);
        }
    }
    public String request(ReptileRequest request){
        ReptileTask task = getReptileTask(request);
        if(task==null){
            this.error(String.format("There are no suitable creeper for this %s group",  request.getCreeperGroup()));
        }
        String taskId = request(task);
        if(taskId==null){
            this.error(String.format("%s request fail,Perhaps the same task is running!",request.getCreeperGroup()));
        }
        return taskId;
    }

    public String request(ReptileTask task){
        if(task!=null){
            if (addTask(task)) {
                return task.getTaskId();
            }
        }
        return null;
    }

    public ReptileTask getReptileTask(ReptileRequest request){
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
                    return new ReptileTask(loadTask,loadConfig,request,loadConfig.getTaskId());
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

    public boolean isRunning(String taskId){
        return runningTask.containsKey(taskId);
    }

}
