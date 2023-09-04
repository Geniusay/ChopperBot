package org.example.core.creeper.loadconfig;

import org.example.core.creeper.loadtask.DouyuHotLiveLoadTask;
import org.example.core.manager.annotation.Creeper;


/**
 * @author Genius
 * @date 2023/07/26 15:33
 **/

@Creeper(creeperName = "douyu_hot_live",loadTask = DouyuHotLiveLoadTask.class,creeperDescription = "斗鱼的热门直播爬虫，可爬取直播和模块热门直播")
public class DouyuHotLiveConfig extends LoadHotModuleConfig {

    public DouyuHotLiveConfig() {
        this.url = "https://www.douyu.com/japi/weblist/apinc/allpage/6/1";
    }

    public DouyuHotLiveConfig(int moduleId){
        this.url = String.format("https://www.douyu.com/gapi/rkc/directory/mixList/2_%s/1",moduleId);
    }
}
