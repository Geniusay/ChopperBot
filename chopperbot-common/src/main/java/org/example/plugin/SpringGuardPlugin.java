package org.example.plugin;

import org.example.thread.ChopperBotGuardianTask;

/**
 * @author Genius
 * @date 2023/09/14 21:46
 **/
public abstract class SpringGuardPlugin extends SpringBootPlugin implements ChopperBotGuardianTask {

    private volatile boolean isStop = false;

    protected boolean afterDo = true;

    public abstract void start();

    @Override
    public boolean init() {
        isStop = false;
        if(!afterDo) this.guardian();
        return true;
    }

    @Override
    public void threadTask() {
        while(!isStop){
            start();
        }
    }

    @Override
    public void shutdown() {
        isStop=true;
    }

    @Override
    public void afterInit() {
        if(afterDo) this.guardian();
    }
}
