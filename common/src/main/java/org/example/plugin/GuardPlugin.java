package org.example.plugin;

import org.example.thread.ChopperBotGuardianTask;

import java.util.List;

/**
 * @author Genius
 * @date 2023/07/31 22:55
 **/
public abstract class GuardPlugin extends CommonPlugin implements ChopperBotGuardianTask {

    private volatile boolean isStop = false;

    protected boolean afterDo = true;

    public GuardPlugin(String module, String pluginName, List<String> needPlugins, boolean isAutoStart) {
        super(module, pluginName, needPlugins, isAutoStart);
    }

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
