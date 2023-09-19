package org.example.core.creeper.loadconfig;

import lombok.Data;
import org.example.core.loadconfig.LoadConfig;

/**
 * @author Genius
 * @date 2023/08/30 18:27
 **/
@Data
public abstract class LoadVideoConfig extends LoadConfig {
    protected String videoPath;

    protected String platform;
    // 视频保存名称
    protected String videoName;

    protected int clarity;

    public LoadVideoConfig(String videoPath, String videoName) {
        super();
        this.videoPath = videoPath;
        this.videoName = videoName;
        this.suffix  = ".flv";
    }

    public String fileName(){
        return videoName+suffix;
    }
}
