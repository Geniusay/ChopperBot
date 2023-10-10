package org.example.core.creeper.loadtask;


import org.example.core.VideoTaskMonitor;
import org.example.core.creeper.loadconfig.HuyaLiveOnlineConfig;
import org.example.core.taskmonitor.Monitor;

/**
 * @author dhx
 * @date 2023/10/10 10:48
 */
@Monitor(clazz = VideoTaskMonitor.class)
public class HuyaLiveOnlineLoadTask extends LiveOnlineLoadTask{
    public HuyaLiveOnlineLoadTask(HuyaLiveOnlineConfig huyaLiveOnlineConfig) {
        super(huyaLiveOnlineConfig);
    }

    @Override
    public String start() {
        return this.start(logger,(HuyaLiveOnlineConfig)loadConfig);
    }

    @Override
    public void end() {

    }
}
