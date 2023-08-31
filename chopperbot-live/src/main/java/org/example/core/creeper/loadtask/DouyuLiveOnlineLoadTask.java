package org.example.core.creeper.loadtask;

import org.example.bean.live.DouyuLive;
import org.example.constpool.PluginName;
import org.example.core.creeper.loadconfig.BilibiliLiveOnlineConfig;
import org.example.core.creeper.loadconfig.DouyuLiveOnlineConfig;
import org.example.core.loadtask.CommonLoadTask;
import org.example.core.loadtask.WebMagicLoadTask;
import org.example.core.manager.LiveDownloadManager;
import org.example.log.ChopperLogFactory;
import org.example.log.LoggerType;
import org.example.plugin.PluginCheckAndDo;
import org.slf4j.Logger;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Genius
 * @date 2023/07/28 23:14
 **/
public class DouyuLiveOnlineLoadTask extends LiveOnlineLoadTask {

    public DouyuLiveOnlineLoadTask(DouyuLiveOnlineConfig douyuLiveOnlineConfig) {
        super(douyuLiveOnlineConfig);

    }

    @Override
    public String start() {
        return this.start(ChopperLogFactory.getLogger(LoggerType.LiveRecord),(DouyuLiveOnlineConfig)loadConfig);
    }

    @Override
    public void end() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public int getCacheSize() {
        return 0;
    }

    @Override
    public int flushCacheAndSave(String key) {
        return 0;
    }
}
