package org.example.core.control.impl;

import org.example.constpool.HotModulePool;
import org.example.core.control.LoadTask;
import org.example.core.processor.hotmodule.DouyuHotLiveProcessor;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;

/**
 * @author Genius
 * @date 2023/07/19 02:42
 **/
public class DouyuHotLiveLoadTask implements LoadTask {

    private String url = "https://www.douyu.com/japi/search/api/getHotList";
    @Override
    public void start() {
        try {
            DouyuHotLiveProcessor douyuHotLiveProcessor = new DouyuHotLiveProcessor();
            Spider.create(douyuHotLiveProcessor)
                    .addRequest(new Request(url))
                    .setEmptySleepTime(10)
                    .thread(1)
                    .run();
        }catch (Exception e){
            e.printStackTrace();
        }

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
        new DouyuHotLiveLoadTask().start();
        System.out.println(HotModulePool.hotLiveListPool);
    }
}
