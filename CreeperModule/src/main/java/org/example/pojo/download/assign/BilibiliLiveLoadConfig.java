package org.example.pojo.download.assign;

import lombok.Data;
import org.example.constpool.ConstPool;
import org.example.pojo.download.LoadConfig;

/**
 * (B站直播)配置信息
 * @author 燧枫
 * @date 2023/4/23 17:59
*/
@Data
public class BilibiliLiveLoadConfig extends LoadConfig {

    // 房间号
    private String roomId;

    public BilibiliLiveLoadConfig(String anchorName, String roomId) {
        super(ConstPool.BILIBILI, ConstPool.ACTION_LIVE, anchorName);
        this.roomId = roomId;
    }
}
