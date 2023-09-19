package org.example.core.creeper.loadconfig;

import org.example.constpool.ConstGroup;
import org.example.constpool.ConstPool;
import org.example.core.creeper.loadtask.DouyuHotModuleLoadTask;
import org.example.core.manager.Creeper;


/**
 * @author Genius
 * @date 2023/06/01 22:16
 **/

@Creeper(creeperName = "斗鱼热门模块爬虫",
        loadTask = DouyuHotModuleLoadTask.class,
        creeperDescription = "斗鱼的热门模块爬虫",
        priority = 10,
        group = ConstGroup.HOT_MODULE,
        platform = ConstPool.DOUYU
)
public class DouyuHotModuleConfig extends LoadHotModuleConfig {

    public DouyuHotModuleConfig() {
        this.url = "https://www.douyu.com/japi/weblist/apinc/header/cate";
    }
}
