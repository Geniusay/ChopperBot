package org.example.core.control.impl;

import org.example.constpool.HotModulePool;
import org.example.core.control.LoadTask;
import org.example.core.processor.hotmodule.DouyuHotModuleProcessor;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;

/**
 * @author Genius
 * @date 2023/07/15 21:04
 **/
public class DouyuHotModuleLoadTask implements LoadTask {

    private String url = "https://www.douyu.com/japi/weblist/apinc/header/cate";
    @Override
    public void start() {
        DouyuHotModuleProcessor douyuHotModuleProcessor = new DouyuHotModuleProcessor();
        Spider.create(douyuHotModuleProcessor)
                .addRequest(new Request(url))
                .setEmptySleepTime(10)
                .thread(1)
                .run();
    }

    @Override
    public void end() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public int getCacheSize() {
        return 0;
    }

    @Override
    public int flushCacheAndSave(String key) {
        return 0;
    }

    public static void main(String[] args) {
        new DouyuHotModuleLoadTask().start();
    }
}
