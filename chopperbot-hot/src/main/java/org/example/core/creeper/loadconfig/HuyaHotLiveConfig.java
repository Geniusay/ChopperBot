package org.example.core.creeper.loadconfig;

import org.example.constpool.ConstGroup;
import org.example.constpool.ConstPool;
import org.example.core.creeper.loadtask.HuyaHotLiveLoadTask;
import org.example.core.creeper.loadtask.HuyaHotModuleLoadTask;
import org.example.core.manager.Creeper;

/**
 * @author Genius
 * @date 2023/10/17 00:47
 **/
@Creeper(creeperName = "虎牙热门直播模块",
        loadTask = HuyaHotLiveLoadTask.class,
        creeperDescription = "获取虎牙的热门直播，按照人气排行，也可以获取热门模块下的直播",
        priority = 10,
        group = ConstGroup.HOT_LIVE,
        platform = ConstPool.HUYA
)
public class HuyaHotLiveConfig extends LoadHotModuleConfig{

    private String moduleId;

    public HuyaHotLiveConfig(String moduleId) {
        this.url = String.format("https://live.huya.com/liveHttpUI/getLiveList?iGid=%s&iPageNo=1&iPageSize=120", moduleId);
    }

    public HuyaHotLiveConfig() {
        moduleId = "0";
        this.url = String.format("https://live.huya.com/liveHttpUI/getLiveList?iGid=%s&iPageNo=1&iPageSize=120", moduleId);
    }

}
