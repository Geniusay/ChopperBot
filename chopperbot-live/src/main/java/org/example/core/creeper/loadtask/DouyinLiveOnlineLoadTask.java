package org.example.core.creeper.loadtask;

import org.example.core.VideoTaskMonitor;
import org.example.core.creeper.loadconfig.DouyinLiveOnlineConfig;
import org.example.core.taskmonitor.Monitor;

/**
 * @author dhx
 * @date 2024/5/26 10:45
 */
@Monitor(clazz = VideoTaskMonitor.class)
public class DouyinLiveOnlineLoadTask extends LiveOnlineLoadTask{
    public DouyinLiveOnlineLoadTask(DouyinLiveOnlineConfig douyinLiveOnlineConfig) {
        super(douyinLiveOnlineConfig);
    }

    @Override
    public String start() {
        return this.start(logger,(DouyinLiveOnlineConfig)loadConfig);
    }

    @Override
    public void end() {

    }
}
