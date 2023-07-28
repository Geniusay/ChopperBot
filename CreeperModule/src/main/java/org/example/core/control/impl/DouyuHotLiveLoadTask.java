package org.example.core.control.impl;

import org.example.bean.Live;
import org.example.bean.hotmodule.DouyuLive;
import org.example.core.control.HotModuleLoadTask;
import org.example.core.processor.hotmodule.DouyuHotLiveProcessor;
import us.codecraft.webmagic.Spider;

import java.util.List;

import static org.example.constpool.ApiPool.*;

/**
 * @author Genius
 * @date 2023/07/19 02:42
 **/
public class DouyuHotLiveLoadTask extends HotModuleLoadTask<List<DouyuLive>> {



    private final DouyuHotLiveProcessor douyuHotLiveProcessor;

    public DouyuHotLiveLoadTask(){
        douyuHotLiveProcessor = new DouyuHotLiveProcessor();
    }

    /**
     * 获取Douyu某个模块下的热门直播
     * @param moduleId
     */
    public List<DouyuLive> start(int moduleId){
        clearFinishFlag();
        douyuHotLiveProcessor.setModuleId(moduleId);
        return this.start(String.format(DOUYU_HOT_MODULE_LIVES_API,moduleId));
    }

    /**
     * 获取Douyu当前最热直播
     */
    @Override
    protected List<DouyuLive> start0() {
        return this.start(DOUYU_HOT_LIVES_API);
    }

    private List<DouyuLive> start(String url){
        List<DouyuLive> lives;
        try {
            lives = getData(Spider.create(douyuHotLiveProcessor),url);
        }catch (Exception e){
            fail(e);
            return null;
        }
        success();
        return lives;
    }

}
