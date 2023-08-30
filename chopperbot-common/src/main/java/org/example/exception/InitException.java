package org.example.exception;

/**
 * @author Genius
 * @date 2023/07/29 14:59
 **/

/**
 * 初始化报错
 */
public class InitException extends RuntimeException{

    private String message;

    public InitException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
