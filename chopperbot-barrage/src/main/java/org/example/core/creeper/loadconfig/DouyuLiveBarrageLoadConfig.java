package org.example.core.creeper.loadconfig;

import lombok.Data;
import org.example.constpool.BarrageModuleConstPool;
import org.example.constpool.ConstGroup;
import org.example.constpool.ConstPool;
import org.example.constpool.CreeperModuleConstPool;
import org.example.core.creeper.loadtask.BilibiliLiveBarrageLoadTask;
import org.example.core.creeper.loadtask.DouyuLiveBarrageLoadTask;
import org.example.core.manager.Creeper;

/**
 * @author Genius
 * @date 2023/09/12 22:05
 **/

@Data
@Creeper(creeperName = "斗鱼直播弹幕爬虫",
        loadTask = DouyuLiveBarrageLoadTask.class,
        creeperDescription = "爬取斗鱼的直播弹幕（采用ws的方式实时获取）",
        priority = 10,
        group = ConstGroup.BARRAGE_ONLINE,
        platform = ConstPool.DOUYU
)
public class DouyuLiveBarrageLoadConfig extends LoadBarrageConfig{

    private String roomId;

    public DouyuLiveBarrageLoadConfig(String anchorName,String roomId) {
        super(ConstPool.DOUYU, CreeperModuleConstPool.ACTION_LIVE, anchorName);
        this.roomId = roomId;
        this.url = "wss://danmuproxy.douyu.com:8502/";
    }
}
