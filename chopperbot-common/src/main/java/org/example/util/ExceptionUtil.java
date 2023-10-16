package org.example.util;

/**
 * @author Genius
 * @date 2023/10/17 01:51
 **/
public class ExceptionUtil {

    public static  <T extends Exception> String getCause(T e){
       return e.getMessage()!=null?e.getMessage():e.getCause()!=null?e.getCause().toString():e.getLocalizedMessage();
    }
}
