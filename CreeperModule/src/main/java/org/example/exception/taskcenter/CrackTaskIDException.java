package org.example.exception.taskcenter;

import org.example.exception.TaskCenterException;

/**
 * @author Genius
 * @date 2023/08/07 00:23
 **/
public class CrackTaskIDException extends TaskCenterException {

    public CrackTaskIDException(String taskId) {
        super("Found crack task ID,taskId:"+taskId);
    }
}
