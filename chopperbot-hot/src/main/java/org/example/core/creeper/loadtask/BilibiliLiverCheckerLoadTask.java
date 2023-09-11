package org.example.core.creeper.loadtask;

import org.example.bean.live.BiliBiliLive;
import org.example.constpool.ConstPool;
import org.example.core.creeper.loadconfig.BilibiliLiverCheckerConfig;
import org.example.core.creeper.processor.BilibiliLiverCheckerProcessor;
import org.example.core.factory.SpiderFactory;
import org.example.core.loadconfig.LoadConfig;
import org.example.core.loadtask.CommonLoadTask;
import org.example.core.loadtask.WebMagicLoadTask;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;

import java.util.Map;


/**
 * @author Genius
 * @date 2023/09/10 01:07
 **/
public class BilibiliLiverCheckerLoadTask extends WebMagicLoadTask<BiliBiliLive> {

    public BilibiliLiverCheckerLoadTask(BilibiliLiverCheckerConfig loadConfig) {
        super(loadConfig);
    }

    @Override
    public BiliBiliLive start() {
        Map<String, String> cookie = loadConfig.getCookie();
        String url = loadConfig.getUrl();
        String liver = ((BilibiliLiverCheckerConfig) loadConfig).getLiver();
        BiliBiliLive live = null;
        Request request = new Request(url);
        cookie.forEach(
                request::addCookie
        );
        BilibiliLiverCheckerProcessor processor = new BilibiliLiverCheckerProcessor(liver);
        Spider spider = SpiderFactory.buildSpider(
                ConstPool.PLATFORM.BILIBILI.getName(),
                processor,
                request
        );
        try {
            live = getData(spider,url);
        }catch (Exception e){
            return null;
        }
        return live;
    }

    @Override
    public void end() {

    }
}
