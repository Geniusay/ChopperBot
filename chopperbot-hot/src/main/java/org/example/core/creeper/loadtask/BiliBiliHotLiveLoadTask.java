package org.example.core.creeper.loadtask;

import org.example.bean.live.BiliBiliLive;
import org.example.constpool.ConstPool;
import org.example.core.creeper.loadconfig.BilibiliHotLiveConfig;
import org.example.core.creeper.processor.BiliBiliHotLiveProcessor;
import org.example.core.factory.SpiderFactory;
import org.example.core.loadconfig.LoadConfig;
import us.codecraft.webmagic.Spider;

import java.util.List;

/**
 * @author Genius
 * @date 2023/09/03 15:59
 **/
public class BiliBiliHotLiveLoadTask extends HotModuleLoadTask<List<BiliBiliLive>> {

    public BiliBiliHotLiveLoadTask(BilibiliHotLiveConfig loadConfig) {
        super(loadConfig);
    }

    @Override
    public List<BiliBiliLive> start() {
        clearFinishFlag();
        List<BiliBiliLive> lives;
        BiliBiliHotLiveProcessor biliBiliHotLiveProcessor = new BiliBiliHotLiveProcessor();
        Spider spider = SpiderFactory.buildSpider(ConstPool.PLATFORM.DOUYU.getName(),
                biliBiliHotLiveProcessor,
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
}
