package org.example.core.taskcenter.task;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.example.constpool.PluginName;
import org.example.core.loadtask.LoadTask;
import org.example.core.taskcenter.TaskCenter;
import org.example.core.taskcenter.task.serializer.TaskStatusEnumDeserializer;
import org.example.core.taskcenter.task.serializer.TaskStatusEnumSerializer;
import org.example.init.InitPluginRegister;
import org.example.core.taskcenter.request.ReptileRequest;
import org.example.util.TimeUtil;

import java.io.Serializable;
import java.util.TreeSet;

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

    private String startTime;
    private String endTime;

    @JSONField(serializeUsing = TaskStatusEnumSerializer.class, deserializeUsing = TaskStatusEnumDeserializer.class)
    private TaskStatus type;


    public ReptileTask(LoadTask loadTask,ReptileRequest request) {
        this.loadTask = loadTask;
        this.request =request;
        this.taskId = request.GenerateTaskId();
        this.type = TaskStatus.Already;
        this.endTime = NULL_TIME;
        this.startTime = NULL_TIME;
    }

    public void reptile(){
        //开始任务
        this.startTime = TimeUtil.getNowTime_YMDHMS();
        this.type = TaskStatus.Running;
        Object res = loadTask.start();

        //完成任务
        request.response(res); //让请求响应结果
        TaskCenter plugin = (TaskCenter) InitPluginRegister.getPlugin(PluginName.TASK_CENTER_PLUGIN);
        plugin.finishTask(taskId);
        this.type = TaskStatus.Finish;
        this.endTime = TimeUtil.getNowTime_YMDHMS();
    }


}
