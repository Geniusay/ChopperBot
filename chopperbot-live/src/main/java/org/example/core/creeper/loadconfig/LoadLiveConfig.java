package org.example.core.creeper.loadconfig;

import lombok.Data;
import org.example.bean.Live;
import org.example.constpool.FileNameBuilder;
import org.example.core.loadconfig.LoadConfig;
import org.example.util.FileUtil;
import org.example.util.TimeUtil;

import java.nio.file.Path;

/**
 * 直播爬取的配置类最底层
 * @author Genius
 * @date 2023/07/28 23:16
 **/
@Data
public abstract class LoadLiveConfig extends LoadVideoConfig {
    // 房间号
    protected String roomId;

    protected String roomName;
    protected String liverName;

    protected String showTime;
    // 是否自动转换为mp4格式
    protected boolean convertToMp4;

    protected boolean showDownloadTable;

    public LoadLiveConfig(String roomId, String videoPath, String videoName, boolean convertToMp4) {
        super(videoPath,videoName);
        this.roomId = roomId;
        this.convertToMp4 = convertToMp4;
        this.showDownloadTable = false;
        this.suffix = convertToMp4?".mp4":suffix;
        this.showTime = this.startTime;
    }

    public LoadLiveConfig(String roomId,String liver, String videoPath, String videoName, boolean convertToMp4) {
        super(videoPath,videoName);
        this.roomId = roomId;
        this.convertToMp4 = convertToMp4;
        this.liverName = liver;
        this.showDownloadTable = false;
        this.suffix = convertToMp4?".mp4":suffix;
        this.showTime = this.startTime;
    }

    public String fullFilePath(){
        return Path.of(videoPath,fileName()).toString();
    }


    public static String fileName(String roomId,String liver,String startTime){
        return FileNameBuilder.buildVideoFileNameNoSuffix(liver,startTime);
    }

    @Override
    public String getTaskId() {
        String taskId = super.getTaskId();
        return String.format(taskId+"_%s",liverName);
    }
}
