package org.example.core.control.hotmodule;

import org.example.bean.HotLive;
import org.example.core.control.HotModuleLoadTask;
import org.example.core.control.LoadTask;
import org.example.core.processor.hotmodule.DouyuHotLiveProcessor;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;

import java.util.List;

import static org.example.constpool.ApiPool.*;

/**
 * @author Genius
 * @date 2023/07/19 02:42
 **/
public class DouyuHotLiveLoadTask extends HotModuleLoadTask<List<HotLive>> {



    private final DouyuHotLiveProcessor douyuHotLiveProcessor;

    public DouyuHotLiveLoadTask(){
        douyuHotLiveProcessor = new DouyuHotLiveProcessor();
    }

    /**
     * 获取Douyu某个模块下的热门直播
     * @param moduleId
     */
    public List<HotLive> start(int moduleId){
        clearFinishFlag();
        douyuHotLiveProcessor.setModuleId(moduleId);
        return this.start(String.format(DOUYU_HOT_MODULE_LIVES_API,moduleId));
    }

    /**
     * 获取Douyu当前最热直播
     */
    @Override
    protected List<HotLive> start0() {
        return this.start(DOUYU_HOT_LIVES_API);
    }

    private List<HotLive> start(String url){
        List<HotLive> lives;
        try {
            lives = getData(Spider.create(douyuHotLiveProcessor),url);
        }catch (Exception e){
            fail(e);
            return null;
        }
        success();
        return lives;
    }

    public static void main(String[] args) {
        List<HotLive> start = new DouyuHotLiveLoadTask().start();
        System.out.println(start);
    }

}
