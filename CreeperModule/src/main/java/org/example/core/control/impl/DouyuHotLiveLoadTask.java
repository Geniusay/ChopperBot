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

    private final String HOT_LIVES_API = "https://www.douyu.com/japi/weblist/apinc/allpage/6/1";             //全部热门直播api
    private final String HOT_MODULE_LIVES_API = "https://www.douyu.com/gapi/rkc/directory/mixList/2_%s/1"; //某个模块热门直播api

    private final DouyuHotLiveProcessor douyuHotLiveProcessor;

    public DouyuHotLiveLoadTask(){
        douyuHotLiveProcessor = new DouyuHotLiveProcessor();
    }

    /**
     * 获取Douyu某个模块下的热门直播
     * @param moduleId
     */
    public void start(int moduleId){
        douyuHotLiveProcessor.setModuleId(moduleId);
        this.start(String.format(HOT_MODULE_LIVES_API,moduleId));
    }

    /**
     * 获取Douyu当前最热直播
     */
    @Override
    public void start() {
       this.start(HOT_LIVES_API);
    }

    private void start(String url){
        try {
            Spider.create(douyuHotLiveProcessor)
                    .addRequest(new Request(url))
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
        new DouyuHotModuleLoadTask().start();
        new DouyuHotLiveLoadTask().start(1);
        System.out.println(HotModulePool.hotModuleListPool);
    }
}
