package org.example.exception;

/**
 * @author Genius
 * @date 2023/04/24 00:57
 **/
public class FileCacheException extends Exception{

    String message;

    public FileCacheException(String ErrorMessage){
        this.message = ErrorMessage;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
