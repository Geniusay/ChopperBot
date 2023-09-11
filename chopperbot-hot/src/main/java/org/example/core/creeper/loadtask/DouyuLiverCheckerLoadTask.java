package org.example.core.creeper.loadtask;

import org.example.bean.live.BiliBiliLive;
import org.example.bean.live.DouyuLive;
import org.example.constpool.ConstPool;
import org.example.core.creeper.loadconfig.BilibiliLiverCheckerConfig;
import org.example.core.creeper.loadconfig.DouyuLiverCheckerConfig;
import org.example.core.creeper.processor.BilibiliLiverCheckerProcessor;
import org.example.core.creeper.processor.DouyuLiverCheckerProcessor;
import org.example.core.factory.SpiderFactory;
import org.example.core.loadconfig.LoadConfig;
import org.example.core.loadtask.WebMagicLoadTask;
import us.codecraft.webmagic.Spider;

import java.util.Map;

/**
 * @author Genius
 * @date 2023/09/10 01:58
 **/
public class DouyuLiverCheckerLoadTask extends WebMagicLoadTask<DouyuLive> {

    public DouyuLiverCheckerLoadTask(DouyuLiverCheckerConfig loadConfig) {
        super(loadConfig);
    }

    @Override
    public DouyuLive start() {
        DouyuLive live = null;
        Spider spider = SpiderFactory.buildSpider(
                ConstPool.PLATFORM.DOUYU.getName(),
                new DouyuLiverCheckerProcessor(),
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
