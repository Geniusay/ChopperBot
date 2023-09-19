package org.example.core.taskcenter.task;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.example.constpool.PluginName;
import org.example.core.loadconfig.LoadConfig;
import org.example.core.loadtask.LoadTask;
import org.example.core.taskcenter.TaskCenter;
import org.example.core.taskcenter.task.serializer.TaskStatusEnumDeserializer;
import org.example.core.taskcenter.task.serializer.TaskStatusEnumSerializer;
import org.example.init.InitPluginRegister;
import org.example.core.taskcenter.request.ReptileRequest;
import org.example.log.ChopperLogFactory;
import org.example.log.LoggerType;
import org.example.util.TimeUtil;

import java.io.Serializable;
import java.util.TreeSet;
import java.util.concurrent.locks.ReentrantLock;

import static org.example.constpool.ConstPool.NULL_TIME;

/**
 * @author Genius
 * @date 2023/07/28 21:51
 **/
@Data
public class ReptileTask implements Serializable {

    private String taskId;
    private ReptileRequest request;
    private LoadTask loadTask;

    private LoadConfig loadConfig;
    private String startTime;
    private String endTime;

    @JSONField(serializeUsing = TaskStatusEnumSerializer.class, deserializeUsing = TaskStatusEnumDeserializer.class)
    private TaskStatus type;

    private ReentrantLock typeLock = new ReentrantLock();
    public ReptileTask(LoadTask loadTask,LoadConfig config,ReptileRequest request,String taskId) {
        this.loadTask = loadTask;
        this.request =request;
        this.taskId = taskId;
        this.type = TaskStatus.No_Status;
        this.endTime = NULL_TIME;
        this.startTime = NULL_TIME;
        this.loadConfig = config;
    }

    public void reptile(){
        try {
            TaskCenter plugin = InitPluginRegister.getPlugin(PluginName.TASK_CENTER_PLUGIN,TaskCenter.class);
            assert plugin != null;
            //开始任务
            this.startTime = TimeUtil.getNowTime_YMDHMS();
            plugin.getTaskCenterLogger().setStartTime(taskId,startTime);
            this.type = TaskStatus.Running;
            plugin.info(String.format("%s start reptile!", taskId));

            //执行爬虫
            Object res = loadTask.start();
            request.response(res); //让请求响应结果

            //完成任务
            plugin.finishTask(taskId);
            plugin.info(String.format("%s end reptile!", taskId));
            this.type = TaskStatus.Finish;
            this.endTime = TimeUtil.getNowTime_YMDHMS();
            plugin.getTaskCenterLogger().setEndTime(taskId,endTime);
        }catch (Exception e){
            ChopperLogFactory.getLogger(LoggerType.Creeper).info("[{}] {} stop, Error:{}",
                    PluginName.TASK_CENTER_PLUGIN,taskId,e.getCause());
        }
    }

    public void end(){
        loadTask.end();
    }

}
