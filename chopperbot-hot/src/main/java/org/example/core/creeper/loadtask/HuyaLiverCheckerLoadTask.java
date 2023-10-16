package org.example.core.creeper.loadtask;

import org.example.bean.live.DouyuLive;
import org.example.bean.live.HuyaLive;
import org.example.constpool.ConstPool;
import org.example.core.creeper.loadconfig.HuyaLiverCheckerConfig;
import org.example.core.creeper.processor.DouyuLiverCheckerProcessor;
import org.example.core.creeper.processor.HuyaLiverCheckerProcessor;
import org.example.core.factory.SpiderFactory;
import org.example.core.loadconfig.LoadConfig;
import org.example.core.loadtask.WebMagicLoadTask;
import us.codecraft.webmagic.Spider;

/**
 * @author Genius
 * @date 2023/10/16 23:01
 **/
public class HuyaLiverCheckerLoadTask extends WebMagicLoadTask<HuyaLive> {

    public HuyaLiverCheckerLoadTask(HuyaLiverCheckerConfig loadConfig) {
        super(loadConfig);

    }

    @Override
    public HuyaLive start() {
        HuyaLive live = null;
        Spider spider = SpiderFactory.buildSpider(
                ConstPool.PLATFORM.HUYA.getName(),
                new HuyaLiverCheckerProcessor(),
                loadConfig.getUrl()
        );
        try {
            live = getData(spider,loadConfig.getUrl());
        }catch (Exception e){
            return null;
        }
        return live;
    }

    @Override
    public void end() {

    }
}
