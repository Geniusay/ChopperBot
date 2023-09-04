package org.example.core.creeper.loadtask;

import org.example.bean.hotmodule.HotModuleList;
import org.example.constpool.ConstPool;
import org.example.core.creeper.loadconfig.DouyuHotModuleConfig;
import org.example.core.creeper.processor.DouyuHotModuleProcessor;
import org.example.core.factory.SpiderFactory;

import us.codecraft.webmagic.Spider;

/**
 * @author Genius
 * @date 2023/07/15 21:04
 **/
public class DouyuHotModuleLoadTask extends HotModuleLoadTask<HotModuleList> {


    public DouyuHotModuleLoadTask(DouyuHotModuleConfig loadConfig) {
        super(loadConfig);

    }

    @Override
    public HotModuleList start() {
        HotModuleList data;
        DouyuHotModuleProcessor douyuHotModuleProcessor = new DouyuHotModuleProcessor();
        clearFinishFlag();
        Spider spider = SpiderFactory.buildSpider(
                ConstPool.PLATFORM.DOUYU.getName(),
                douyuHotModuleProcessor,
                loadConfig.getUrl());
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
