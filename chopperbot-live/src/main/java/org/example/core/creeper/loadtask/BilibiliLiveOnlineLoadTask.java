package org.example.core.creeper.loadtask;

import org.example.core.VideoTaskMonitor;
import org.example.core.creeper.loadconfig.BilibiliLiveOnlineConfig;
import org.example.core.taskmonitor.Monitor;

/**
 * @author Genius
 * @date 2023/08/31 15:44
 **/
@Monitor(clazz = VideoTaskMonitor.class)
public class BilibiliLiveOnlineLoadTask extends LiveOnlineLoadTask {

    public BilibiliLiveOnlineLoadTask(BilibiliLiveOnlineConfig loadConfig) {
        super(loadConfig);
    }

    @Override
    public String start() {
        return this.start(logger,(BilibiliLiveOnlineConfig)loadConfig);
    }

    @Override
    public void end() {

    }
}
