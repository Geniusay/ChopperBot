package org.example.core.creeper.loadtask;

import org.checkerframework.checker.units.qual.A;
import org.example.constpool.PluginName;
import org.example.core.VideoTaskMonitor;
import org.example.core.creeper.loadconfig.BilibiliLiveOnlineConfig;
import org.example.core.loadtask.CommonLoadTask;
import org.example.core.manager.LiveDownloadManager;
import org.example.core.taskmonitor.Monitor;
import org.example.log.ChopperLogFactory;
import org.example.log.LoggerType;
import org.example.plugin.PluginCheckAndDo;
import org.slf4j.Logger;

import java.util.concurrent.atomic.AtomicReference;

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
        return this.start(ChopperLogFactory.getLogger(LoggerType.LiveRecord),(BilibiliLiveOnlineConfig)loadConfig);
    }

    @Override
    public void end() {

    }
}
