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

    // 视频保存名称
    protected String videoName;

    protected int clarity;

    public LoadVideoConfig(String videoPath, String videoName) {
        this.videoPath = videoPath;
        this.videoName = videoName;
    }
}
