package org.example.pojo.liveConfig;

import lombok.Data;

/**
 * b站直播下载配置
 * @author 燧枫
 * @date 2023/5/19 19:55
*/
@Data
public class BilibiliLiveConfig extends LiveConfig {

    // 清晰度,10000为原画画质
    private int clarity;

    public BilibiliLiveConfig(String roomId, int clarity, String videoPath, boolean convertToMp4) {
        super(roomId, videoPath, convertToMp4);
        this.clarity = clarity;
    }

    public BilibiliLiveConfig(String roomId, String videoPath) {
        super(roomId, videoPath, true);
        this.clarity = 10000;
    }
}
