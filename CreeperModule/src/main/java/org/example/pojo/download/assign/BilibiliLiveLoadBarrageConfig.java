package org.example.pojo.download.assign;

import lombok.Data;
import org.example.constpool.ConstPool;
import org.example.pojo.download.LoadBarrageConfig;

/**
 * (B站直播)配置信息
 * @author 燧枫
 * @date 2023/4/23 17:59
*/
@Data
public class BilibiliLiveLoadBarrageConfig extends LoadBarrageConfig {

    // 房间号
    private String roomId;

    public BilibiliLiveLoadBarrageConfig(String anchorName, String roomId) {
        super(ConstPool.BILIBILI, ConstPool.ACTION_LIVE, anchorName);
        this.roomId = roomId;
    }
}
