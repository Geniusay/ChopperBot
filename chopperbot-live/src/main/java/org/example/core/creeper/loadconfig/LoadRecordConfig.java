package org.example.core.creeper.loadconfig;

import lombok.Data;
import org.example.core.loadconfig.LoadConfig;

import java.nio.file.Path;

/**
 * @author Genius
 * @date 2023/08/30 16:28
 **/
@Data
public abstract class LoadRecordConfig extends LoadVideoConfig {



    public LoadRecordConfig(String downloadPath, String fileName,String url) {
        super(downloadPath,fileName);
        this.url = url;
    }

    public String getRecordPath(){
        return Path.of(videoPath,videoName).toString();
    }
}
