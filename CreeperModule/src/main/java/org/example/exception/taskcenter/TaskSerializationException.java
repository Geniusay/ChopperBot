package org.example.exception.taskcenter;

import org.example.exception.TaskCenterException;

/**
 * @author Genius
 * @date 2023/08/10 16:37
 **/
public class TaskSerializationException extends TaskCenterException {

    private String taskId;

    public TaskSerializationException(String taskId,String msg) {
        super(taskId+" not serializable Error:"+msg);
        this.taskId = taskId;
    }

}
