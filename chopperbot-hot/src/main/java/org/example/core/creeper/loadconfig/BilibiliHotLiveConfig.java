package org.example.core.creeper.loadconfig;

import org.example.constpool.ConstGroup;
import org.example.constpool.ConstPool;
import org.example.core.creeper.loadtask.BiliBiliHotLiveLoadTask;
import org.example.core.manager.Creeper;

/**
 * @author Genius
 * @date 2023/09/03 13:10
 **/
@Creeper(creeperName = "B站热门直播",
        loadTask = BiliBiliHotLiveLoadTask.class,
        creeperDescription = "爬取B站当前热门直播，按人气排行",
        priority = 10,
        group = ConstGroup.HOT_LIVE,
        platform = ConstPool.BILIBILI
)
public class BilibiliHotLiveConfig extends LoadHotModuleConfig{


    public BilibiliHotLiveConfig() {
        this.url = "https://api.live.bilibili.com/xlive/web-interface/v1/second/getListByArea?sort=online&page=1&page_size=100&platform=web";
    }

    public BilibiliHotLiveConfig(String parent_area_id,String area_id,int page){
        this.url = String.format("https://api.live.bilibili.com/xlive/web-interface/v1/second/getList?" +
                "platform=web&parent_area_id=%s&area_id=%s&page=%s",parent_area_id,area_id,page);
    }
}
