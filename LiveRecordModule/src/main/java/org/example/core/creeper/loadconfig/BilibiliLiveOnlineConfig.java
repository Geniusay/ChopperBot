package org.example.core.creeper.loadconfig;

import lombok.Data;

/**
 * @author Genius
 * @date 2023/08/30 18:05
 **/
@Data
public class BilibiliLiveOnlineConfig extends LoadLiveConfig{


    public BilibiliLiveOnlineConfig(String roomId, String videoPath, String videoName,int clarity) {
        super(roomId, videoPath, videoName, false);
        this.clarity = clarity;
        setHeader();
    }

    public BilibiliLiveOnlineConfig(String roomId, String videoPath, String videoName,boolean convertToMp4) {
        super(roomId, videoPath, videoName, convertToMp4);
        this.clarity = 4000;
        setHeader();
    }

    private void setHeader(){
        this.UserAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36";
        this.Origin = "https://live.bilibili.com";
        this.Referer = "https://live.bilibili.com/";
    }

}
