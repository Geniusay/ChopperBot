package org.example.core.creeper.loadtask;

import org.example.bean.hotmodule.HotModuleList;
import org.example.constpool.ConstPool;
import org.example.core.creeper.loadconfig.DouyuHotModuleConfig;
import org.example.core.creeper.processor.DouyuHotModuleProcessor;
import org.example.core.factory.SpiderFactory;
import org.example.core.loadtask.HotModuleLoadTask;

import org.example.pojo.download.LoadConfig;
import us.codecraft.webmagic.Spider;

import static org.example.constpool.ApiPool.DOUYU_HOT_MODULE_API;

/**
 * @author Genius
 * @date 2023/07/15 21:04
 **/
public class DouyuHotModuleLoadTask extends HotModuleLoadTask<HotModuleList> {

    private DouyuHotModuleProcessor douyuHotModuleProcessor;

    public DouyuHotModuleLoadTask(DouyuHotModuleConfig loadConfig) {
        super(loadConfig);
        douyuHotModuleProcessor = new DouyuHotModuleProcessor();
    }

    @Override
    public HotModuleList start() {
        HotModuleList data;
        Spider spider = SpiderFactory.buildSpider(ConstPool.PLATFORM.DOUYU.getName(),
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
