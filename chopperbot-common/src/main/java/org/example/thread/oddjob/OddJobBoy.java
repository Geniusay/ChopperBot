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
public class OddJobBoy implements ChopperBotGuardianTask {

    private static volatile OddJobBoy Instance;

    private BlockingQueue<OddJob> oddjobs;

    private OddJobBoy(){
        oddjobs = new ArrayBlockingQueue<>(1024);
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
        oddjobs.put(job);
    }


    @Override
    public void threadTask() {
        while(true){
            try {
                OddJob job = oddjobs.take();
                ChopperLogFactory.getLogger(LoggerType.System).info("<OddJobBoy> boy get a odd job:{},Processing...",job);
                job.doJob();
            }catch (InterruptedException e){

            }
        }
    }
}
