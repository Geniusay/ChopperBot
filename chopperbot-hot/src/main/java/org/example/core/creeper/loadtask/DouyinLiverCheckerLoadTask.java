package org.example.core.creeper.loadtask;

import org.example.bean.live.DouyinLive;
import org.example.constpool.ConstPool;
import org.example.core.creeper.loadconfig.DouyinLiverCheckerConfig;
import org.example.core.creeper.processor.DouyinLiverCheckerProcessor;
import org.example.core.factory.SpiderFactory;
import org.example.core.loadtask.WebMagicLoadTask;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Request;

/**
 * @author dhx
 * @date 2024/5/26 11:08
 */
public class DouyinLiverCheckerLoadTask extends WebMagicLoadTask<DouyinLive> {

    public DouyinLiverCheckerLoadTask(DouyinLiverCheckerConfig loadConfig) {
        super(loadConfig);
    }

    @Override
    public DouyinLive start() {
        DouyinLive live = null;
        Request request = new Request(loadConfig.getUrl());
        request.addHeader("rid",loadConfig.getHeader().get("rid"));
        request.addHeader("Cookie",loadConfig.getHeader().get("Cookie"));
        Spider spider = SpiderFactory.buildSpider(
                ConstPool.PLATFORM.DOUYIN.getName(),
                new DouyinLiverCheckerProcessor(),
                request
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
