package org.example.core.creeper.loadconfig;

import org.example.constpool.ConstGroup;
import org.example.constpool.ConstPool;
import org.example.core.creeper.loadtask.BilibiliHotModuleLoadTask;
import org.example.core.creeper.loadtask.HuyaHotModuleLoadTask;
import org.example.core.manager.Creeper;

/**
 * @author Genius
 * @date 2023/10/17 00:39
 **/

@Creeper(creeperName = "虎牙热门模块",
        loadTask = HuyaHotModuleLoadTask.class,
        creeperDescription = "获取虎牙的热门模块，按照人气排行",
        priority = 10,
        group = ConstGroup.HOT_MODULE,
        platform = ConstPool.HUYA
)
public class HuyaHotModuleConfig extends LoadHotModuleConfig{

    public HuyaHotModuleConfig() {
        this.url = "https://www.huya.com/g";
    }
}
