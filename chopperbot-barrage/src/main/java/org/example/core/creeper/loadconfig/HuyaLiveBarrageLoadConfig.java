package org.example.core.creeper.loadconfig;

import lombok.Data;
import org.example.constpool.ConstGroup;
import org.example.constpool.ConstPool;
import org.example.constpool.CreeperModuleConstPool;
import org.example.core.creeper.loadtask.HuyaLiveBarrageLoadTask;
import org.example.core.manager.Creeper;

/**
 * @author dhx
 * @date 2023/10/16 15:59
 */
@Data
@Creeper(creeperName = "虎牙直播弹幕爬虫",
        loadTask = HuyaLiveBarrageLoadTask.class,
        creeperDescription = "爬取斗鱼的直播弹幕（采用ws的方式实时获取）",
        priority = 10,
        group = ConstGroup.BARRAGE_ONLINE,
        platform = ConstPool.HUYA
)
public class HuyaLiveBarrageLoadConfig extends LoadBarrageConfig{
    private String roomId;

    public HuyaLiveBarrageLoadConfig(String anchorName,String roomId) {
        super(ConstPool.HUYA, CreeperModuleConstPool.ACTION_LIVE, anchorName);
        this.roomId = roomId;
        this.url = "wss://cdnws.api.huya.com/";
    }
}
