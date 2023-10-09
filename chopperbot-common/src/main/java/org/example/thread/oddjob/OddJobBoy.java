package org.example.thread.oddjob;

import org.example.log.ChopperLogFactory;
import org.example.log.LoggerType;
import org.example.thread.ChopperBotGuardianTask;
import org.example.thread.NamedThreadFactory;

import java.util.concurrent.*;

/**
 * @author Genius
 * @date 2023/07/21 19:18
 **/

/**
 * ChopperBot系统中专门用来处理异步事件的类
 */
public class OddJobBoy {

    private static volatile OddJobBoy Instance;


    private ExecutorService pool;

    private OddJobBoy(){
        pool = Executors.newCachedThreadPool(new NamedThreadFactory("oddjob"));
    }

    public static OddJobBoy Boy(){
        if(Instance==null){
            synchronized (OddJobBoy.class){
                if(Instance==null){
                    Instance = new OddJobBoy();
                }
            }
        }
        return Instance;
    }

    public void addWork(OddJob job) throws InterruptedException {
        pool.submit(job::doJob);
    }
}
