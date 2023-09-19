package org.example.core.creeper.loadconfig;

import org.example.constpool.ConstGroup;
import org.example.constpool.ConstPool;
import org.example.core.creeper.loadtask.BilibiliHotModuleLoadTask;
import org.example.core.manager.Creeper;

/**
 * @author Genius
 * @date 2023/06/01 22:16
 **/
@Creeper(creeperName = "B站热门模块",
        loadTask = BilibiliHotModuleLoadTask.class,
        creeperDescription = "获取b站的热门模块，按照人气排行",
        priority = 10,
        group = ConstGroup.HOT_MODULE,
        platform = ConstPool.BILIBILI
)
public class BilibiliHotModuleConfig extends LoadHotModuleConfig{
    public BilibiliHotModuleConfig() {
        this.url = "https://api.live.bilibili.com/xlive/web-interface/v1/index/getWebAreaList?source_id=2";
    }
}
