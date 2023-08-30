package org.example.pojo.live;

import lombok.Data;

/**
 *
 * @author 燧枫
 * @date 2023/8/3 22:23
 */
@Data
public class DouyuRecordConfig extends LiveConfig {

    private String startTime;

    private String endTime;

    public DouyuRecordConfig(String roomId, String videoPath, String videoName, String startTime, String endTime) {
        super(roomId, videoPath, videoName, false);
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
