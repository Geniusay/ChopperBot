package org.example.core.creeper.loadtask;

import org.example.bean.live.DouyuLive;
import org.example.constpool.ConstPool;
import org.example.core.creeper.loadconfig.DouyuHotLiveConfig;
import org.example.core.creeper.processor.DouyuHotLiveProcessor;
import org.example.core.factory.SpiderFactory;

import us.codecraft.webmagic.Spider;

import java.util.List;

/**
 * @author Genius
 * @date 2023/07/19 02:42
 **/

/**
 * 斗鱼的热门直播爬取任务
 * 1,全部的热门直播爬取
 * 2,指定对应模块下的热门直播爬取
 */
public class DouyuHotLiveLoadTask extends HotModuleLoadTask<List<DouyuLive>> {

    private final DouyuHotLiveProcessor douyuHotLiveProcessor;

    public DouyuHotLiveLoadTask(DouyuHotLiveConfig douyuHotLiveConfig){
        super(douyuHotLiveConfig);
        douyuHotLiveProcessor = new DouyuHotLiveProcessor();
    }


    /**
     * 获取Douyu当前最热直播
     */
    @Override
    public List<DouyuLive> start(){
        clearFinishFlag();
        List<DouyuLive> lives;
        Spider spider = SpiderFactory.buildSpider(ConstPool.PLATFORM.DOUYU.getName(),
                douyuHotLiveProcessor,
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
