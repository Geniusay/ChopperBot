package org.example.core.creeper.loadconfig;

import lombok.Data;
import org.example.bean.Live;
import org.example.core.loadconfig.LoadConfig;

/**
 * 直播爬取的配置类最底层
 * @author Genius
 * @date 2023/07/28 23:16
 **/
@Data
public abstract class LoadLiveConfig extends LoadVideoConfig {
    // 房间号
    protected String roomId;

    protected String liverName;


    // 是否自动转换为mp4格式
    protected boolean convertToMp4;

    protected boolean showDownloadTable;

    public LoadLiveConfig(String roomId, String videoPath, String videoName, boolean convertToMp4) {
        super(videoPath,videoName);
        this.roomId = roomId;
        this.convertToMp4 = convertToMp4;
        this.showDownloadTable = false;
    }
}
