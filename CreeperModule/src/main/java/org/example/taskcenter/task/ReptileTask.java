package org.example.taskcenter.task;

import lombok.Data;
import org.example.core.control.LoadTask;
import org.example.taskcenter.TaskCenter;
import org.example.taskcenter.request.ReptileRequest;
import org.example.util.TimeUtil;

import java.time.LocalDateTime;

import static org.example.constpool.ConstPool.NULL_TIME;

/**
 * @author Genius
 * @date 2023/07/28 21:51
 **/
@Data
public class ReptileTask {

    public enum TaskStatus{
        Already,//准备就绪
        Running,//正在运行
        Finish,//完成的任务
        No_Status //没有状态，用于不需要恢复的任务
    }
    private String taskId;
    private ReptileRequest request;
    private LoadTask loadTask;

    private String startTime;
    private String endTime;
    private TaskStatus type;


    public ReptileTask(LoadTask loadTask,ReptileRequest request) {
        this.loadTask = loadTask;
        this.request =request;
        this.taskId = request.GenerateTaskId();
        this.type = TaskStatus.Already;
        this.endTime = NULL_TIME;
    }

    public void reptile(){
        //开始任务
        this.startTime = TimeUtil.getNowTime_YMDHMS();
        this.type = TaskStatus.Running;
        Object res = loadTask.start();

        //完成任务
        request.response(res); //让请求响应结果
        TaskCenter.center().finishTask(taskId);
        this.type = TaskStatus.Finish;
        this.endTime = TimeUtil.getNowTime_YMDHMS();
    }

}
