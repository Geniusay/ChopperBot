package org.example.exception;

/**
 * @author Genius
 * @date 2023/08/07 00:22
 **/
public class TaskCenterException extends RuntimeException{
    private String msg;

    public TaskCenterException(String msg) {
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return msg;
    }
}
