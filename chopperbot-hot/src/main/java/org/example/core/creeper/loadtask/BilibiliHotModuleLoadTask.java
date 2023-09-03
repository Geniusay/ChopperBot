package org.example.core.creeper.loadtask;

import org.example.bean.hotmodule.HotModuleList;
import org.example.constpool.ConstPool;
import org.example.core.creeper.loadconfig.BilibiliHotModuleConfig;
import org.example.core.creeper.processor.BilibiliHotModuleProcessor;
import org.example.core.factory.SpiderFactory;
import org.example.core.loadconfig.LoadConfig;
import us.codecraft.webmagic.Spider;

/**
 * @author Genius
 * @date 2023/09/03 14:06
 **/
public class BilibiliHotModuleLoadTask extends HotModuleLoadTask<HotModuleList> {

    public BilibiliHotModuleLoadTask(BilibiliHotModuleConfig loadConfig) {
        super(loadConfig);
    }

    @Override
    public HotModuleList start() {
        HotModuleList data;
        BilibiliHotModuleProcessor bilibiliHotModuleProcessor = new BilibiliHotModuleProcessor();
        clearFinishFlag();
        Spider spider = SpiderFactory.buildSpider(
                ConstPool.PLATFORM.BILIBILI.getName(),
                bilibiliHotModuleProcessor,
                loadConfig.getUrl()
        );
        try {
            data = getData(spider,loadConfig.getUrl());
        }catch (Exception e){
            fail(e);
            return null;
        }
        success();
        return data;
    }
}
