package org.example.core.control.impl;

import org.example.bean.hotmodule.DouyuLive;
import org.example.core.control.LoadTask;
import org.example.log.ChopperLogFactory;
import org.example.log.LoggerType;
import org.example.pojo.download.assign.DouyuLiveOnlineConfig;
import org.slf4j.LoggerFactory;

/**
 * @author Genius
 * @date 2023/07/28 23:14
 **/
public class DouyuLiveOnlineLoadTask implements LoadTask {


    private DouyuLive douyuLive;
    public DouyuLiveOnlineLoadTask(DouyuLiveOnlineConfig douyuLiveOnlineConfig) {
        this.douyuLive = douyuLiveOnlineConfig.getLive();
    }

    //TODO 需要开发斗鱼在线直播爬取功能
    @Override
    public Object start() {
        ChopperLogFactory
                .getLogger(LoggerType.Creeper).info("正在爬取主播：{},直播间:{},直播间id:{}",douyuLive.getLiver(),douyuLive.getLiveName(),douyuLive.getLiveId());
        return null;
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
