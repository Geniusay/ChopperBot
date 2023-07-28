package org.example.thread;

/**
 * @author Genius
 * @date 2023/07/24 23:51
 **/

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * ChopperBot系统的线程池
 * TODO 待完善
 */
public class ChopperBotThreadPool {

    public static ExecutorService threadPool = Executors.newFixedThreadPool(10);
}
