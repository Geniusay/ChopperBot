package org.example.thread.oddjob;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Genius
 * @date 2023/07/21 19:18
 **/

/**
 * ChopperBot系统中专门用来处理异步事件的类
 */
public class OddJobBoy {

    private static volatile OddJobBoy Instance;

    private BlockingQueue<OddJob> oddjobs;

    private ExecutorService home;

    private OddJobBoy(){
        home = Executors.newSingleThreadExecutor();
        oddjobs = new ArrayBlockingQueue<>(1024);
    }

    public static OddJobBoy Boy(){
        if(Instance==null){
            synchronized (OddJobBoy.class){
                if(Instance==null){
                    Instance = new OddJobBoy();
                    Instance.work();
                }
            }
        }
        return Instance;
    }

    public void addWork(OddJob job) throws InterruptedException {
        oddjobs.put(job);
    }

    private void work(){
        home.submit(new Boy());
    }

    public boolean relax(){
        home.shutdown();
        return home.isShutdown();
    }

    class Boy implements Runnable{

        @Override
        public void run() {
            while(true){
                try {
                    OddJob job = oddjobs.take();
                    job.doJob();
                }catch (InterruptedException e){

                }
            }
        }
    }
}
