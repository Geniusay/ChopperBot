package org.example.pojo.live;

import lombok.Data;

@Data
public class DouyuLiveConfig extends LiveConfig{

    // 清晰度,4000蓝光
    private int clarity;

    public DouyuLiveConfig(String roomId, int clarity, String videoPath, String videoName, boolean convertToMp4) {
        super(roomId, videoPath, videoName, convertToMp4);
        this.clarity = clarity;
    }

    public DouyuLiveConfig(String roomId, String videoPath, String videoName) {
        super(roomId, videoPath, videoName, true);
        this.clarity = 4000;
    }
}
