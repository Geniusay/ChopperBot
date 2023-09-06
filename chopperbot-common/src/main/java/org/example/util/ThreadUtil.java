package org.example.util;

/**
 * @author Genius
 * @date 2023/09/06 19:09
 **/
public class ThreadUtil {
    public static boolean isInterrupt(){
        return Thread.currentThread().isInterrupted();
    }
}
