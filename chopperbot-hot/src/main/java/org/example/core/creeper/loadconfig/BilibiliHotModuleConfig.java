package org.example.core.creeper.loadconfig;

import org.example.core.creeper.loadtask.BilibiliHotModuleLoadTask;
import org.example.core.manager.Creeper;

/**
 * @author Genius
 * @date 2023/06/01 22:16
 **/
@Creeper(creeperName = "bilibili_hot_module",loadTask = BilibiliHotModuleLoadTask.class,creeperDescription = "b站热门直播模块")
public class BilibiliHotModuleConfig extends LoadHotModuleConfig{
    public BilibiliHotModuleConfig() {
        this.url = "https://api.live.bilibili.com/xlive/web-interface/v1/index/getWebAreaList?source_id=2";
    }
}
