package org.example.core.creeper.loadtask;

import org.example.bean.live.BiliBiliLive;
import org.example.bean.live.HuyaLive;
import org.example.constpool.ConstPool;
import org.example.core.creeper.loadconfig.HuyaHotLiveConfig;
import org.example.core.creeper.processor.BiliBiliHotLiveProcessor;
import org.example.core.creeper.processor.HuyaHotLiveProcessor;
import org.example.core.factory.SpiderFactory;
import org.example.core.loadconfig.LoadConfig;
import org.example.core.loadtask.WebMagicLoadTask;
import us.codecraft.webmagic.Spider;

import java.util.List;

/**
 * @author Genius
 * @date 2023/10/17 00:50
 **/
public class HuyaHotLiveLoadTask extends HotModuleLoadTask<List<HuyaLive>> {

    public HuyaHotLiveLoadTask(HuyaHotLiveConfig loadConfig) {
        super(loadConfig);
    }

    @Override
    public List<HuyaLive> start() {
        clearFinishFlag();
        List<HuyaLive> lives;
        HuyaHotLiveProcessor huyaHotLiveProcessor = new HuyaHotLiveProcessor();
        Spider spider = SpiderFactory.buildSpider(ConstPool.PLATFORM.HUYA.getName(),
                huyaHotLiveProcessor,
                loadConfig.getUrl());
        try {
            lives = getData(spider,loadConfig.getUrl());
        }catch (Exception e){
            fail(e);
            return null;
        }
        success();
        return lives;
    }

    @Override
    public void end() {

    }
}
