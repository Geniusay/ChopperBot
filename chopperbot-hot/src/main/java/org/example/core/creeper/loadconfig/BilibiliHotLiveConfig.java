package org.example.core.creeper.loadconfig;

import org.example.core.creeper.loadtask.BiliBiliHotLiveLoadTask;
import org.example.core.creeper.loadtask.BilibiliHotModuleLoadTask;
import org.example.core.manager.annotation.Creeper;

/**
 * @author Genius
 * @date 2023/09/03 13:10
 **/
@Creeper(creeperName = "bilibili_hot_live",loadTask = BiliBiliHotLiveLoadTask.class,creeperDescription = "b站热门直播")
public class BilibiliHotLiveConfig extends LoadHotModuleConfig{


    public BilibiliHotLiveConfig() {
        this.url = "https://api.live.bilibili.com/xlive/web-interface/v1/second/getListByArea?sort=online&page=1&page_size=100&platform=web";
    }

    public BilibiliHotLiveConfig(String parent_area_id,String area_id,int page){
        this.url = String.format("https://api.live.bilibili.com/xlive/web-interface/v1/second/getList?" +
                "platform=web&parent_area_id=%s&area_id=%s&page=%s",parent_area_id,area_id,page);
    }
}
