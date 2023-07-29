package org.example.thread;

/**
 * @author Genius
 * @date 2023/07/24 23:51
 **/

import lombok.Data;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * ChopperBot系统的线程池
 * TODO 待完善
 */
@Data
public class ChopperBotGuardPool {

    private ExecutorService threadPool;

    private volatile static ChopperBotGuardPool pool;

    private ChopperBotGuardPool() {
        this.threadPool = Executors.newFixedThreadPool(10,new NamedThreadFactory("GuardThread"));
    }

    public static ChopperBotGuardPool GuardPool(){
        if(pool==null){
            synchronized (ChopperBotGuardPool.class){
                if(pool==null){
                    init();
                }
            }
        }
        return pool;
    }
    public static void init(){
        pool = new ChopperBotGuardPool();
    }

    public ExecutorService getThreadPool() {
        return threadPool;
    }

    public void shutdown(){
        threadPool.shutdown();
    }

}
