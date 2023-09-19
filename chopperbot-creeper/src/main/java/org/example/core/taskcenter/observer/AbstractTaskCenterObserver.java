package org.example.core.taskcenter.observer;

import org.example.core.taskcenter.TaskCenter;
import org.example.core.taskcenter.task.ReptileTask;
import org.example.core.taskcenter.task.TaskStatus;

import javax.annotation.Resource;

/**
 * @author Genius
 * @date 2023/09/14 21:12
 **/
public abstract class AbstractTaskCenterObserver implements TaskCenterObserver {

    @Resource
    protected TaskCenter taskCenter;

    @Override
    public void listen(TaskStatus status, ReptileTask task) {
        if (isMe(task.getTaskId())) {
            if(status==TaskStatus.Already){
                onAlready(task);
            }else if(status==TaskStatus.Running){
                onRunning(task);
            }else if(status==TaskStatus.Finish){
                onFinish(task);
            }
        }
    }

    public abstract void onAlready(ReptileTask task);

    public abstract void onRunning(ReptileTask task);

    public abstract void onFinish(ReptileTask task);
}
