package org.example.core.taskcenter.observer;

import org.example.core.taskcenter.task.ReptileTask;
import org.example.core.taskcenter.task.TaskStatus;

/**
 * @author Genius
 * @date 2023/09/14 21:10
 **/
public interface TaskCenterObserver {


    void listen(TaskStatus status, ReptileTask task);

    void send();

    boolean isMe(String taskId);
}
