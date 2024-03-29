package org.example.core.creeper.loadconfig;

import lombok.Data;
import org.example.constpool.ConstGroup;
import org.example.constpool.ConstPool;
import org.example.constpool.CreeperModuleConstPool;
import org.example.core.creeper.builder.BilibiliLiveBarrageLoadConfigBuilder;
import org.example.core.creeper.loadtask.BilibiliLiveBarrageLoadTask;
import org.example.core.manager.Creeper;

/**
 * (B站直播)配置信息
 * @author 燧枫
 * @date 2023/4/23 17:59
*/
@Data
@Creeper(creeperName = "B站直播弹幕爬虫",
        loadTask = BilibiliLiveBarrageLoadTask.class,
        creeperDescription = "爬取B站的直播弹幕内容(WebMagic爬虫，包含监控器)",
        priority = 10,
        group = ConstGroup.BARRAGE_ONLINE,
        platform = ConstPool.BILIBILI
)
public class BilibiliLiveLoadBarrageConfig extends LoadBarrageConfig {

    // 房间号
    private String roomId;

    public BilibiliLiveLoadBarrageConfig(String anchorName, String roomId) {
        super(CreeperModuleConstPool.BILIBILI, CreeperModuleConstPool.ACTION_LIVE, anchorName);
        this.roomId = roomId;
        this.url = "https://api.live.bilibili.com/ajax/msg?roomid="+roomId;
    }
}
