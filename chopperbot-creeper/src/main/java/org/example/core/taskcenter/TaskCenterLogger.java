package org.example.core.taskcenter;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.cache.FileCache;
import org.example.cache.FileCacheManagerInstance;
import org.example.config.CreeperConfigFile;
import org.example.config.CreeperLogConfigFile;
import org.example.core.taskcenter.task.ReptileTask;
import org.example.core.taskcenter.task.TaskRecord;
import org.example.core.taskcenter.task.TaskStatus;
import org.example.core.taskcenter.task.serializer.TaskStatusEnumDeserializer;
import org.example.core.taskcenter.task.serializer.TaskStatusEnumSerializer;
import org.example.exception.FileCacheException;
import org.example.log.ChopperLogFactory;
import org.example.log.LoggerType;
import org.example.util.ConfigFileUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Genius
 * @date 2023/09/10 19:24
 **/
@Data
public class TaskCenterLogger {

    private FileCache creeperLogFileCache; //爬虫日志文件

    private Map<String,TaskLog> taskLogMap;

    private ReentrantLock lock;

    private Map<String, ReptileTask> runningTask;

    private BlockingQueue<ReptileTask> waitingTask;

    public TaskCenterLogger(BlockingQueue<ReptileTask> waitingTask,Map<String, ReptileTask> runningTask) {
        taskLogMap = new ConcurrentHashMap<>();
        lock = new ReentrantLock();
        if(!newLogFile(new CreeperLogConfigFile(new ArrayList<>()))){
            throw new RuntimeException();
        }
        this.runningTask = runningTask;
        this.waitingTask = waitingTask;
    }

    public boolean newLogFile(CreeperLogConfigFile configFile){
        if (ConfigFileUtil.createConfigFile(CreeperLogConfigFile.getFullFilePath(),configFile)) {
            creeperLogFileCache = FileCacheManagerInstance.getInstance().getFileCache(CreeperLogConfigFile.getFullFilePath());
            return true;
        }
        return false;
    }

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

    public void appendLog(TaskLog log){
        if(log!=null){
            try {
                lock.lock();
                if (taskLogMap.putIfAbsent(log.getTaskId(), log)!=null) return;
                checkAndUpdateLogFile();
                creeperLogFileCache.append(log,"task","-1");
            } catch (Exception e) {
                ChopperLogFactory.getLogger(LoggerType.Creeper).error("[TaskCenter] Error {} cant serializable",log.getTaskId());
            } finally {
                lock.unlock();
            }
        }
    }

    public void syncLog(){
        try {
            lock.lock();
            checkAndUpdateLogFile();
            creeperLogFileCache.writeSyncFlag();
        } finally {
            lock.unlock();
        }
    }

    public void setStartTime(String taskId,String startTime){
        taskLogMap.get(taskId).setStartTime(startTime);
    }

    public void setEndTime(String taskId,String endTime){
        taskLogMap.get(taskId).setEndTime(endTime);
    }
}
