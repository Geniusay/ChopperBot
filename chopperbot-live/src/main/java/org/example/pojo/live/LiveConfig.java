package org.example.pojo.live;

import lombok.Data;

/**
 * 直播下载配置
 * @author 燧枫
 * @date 2023/5/19 19:54
*/
@Data
public class LiveConfig {

    // 房间号
    private String roomId;

    // 视频保存路径
    private String videoPath;

    // 视频保存名称
    private String videoName;

    // 是否自动转换为mp4格式
    private boolean convertToMp4;

    public LiveConfig(String roomId, String videoPath, String videoName, boolean convertToMp4) {
        this.roomId = roomId;
        this.videoPath = videoPath;
        this.videoName = videoName;
        this.convertToMp4 = convertToMp4;
    }
}
