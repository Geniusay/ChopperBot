package org.example.core.creeper.loadconfig;

import lombok.Data;
import org.example.bean.live.DouyuLive;
import org.example.core.creeper.loadtask.DouyuLiveOnlineLoadTask;
import org.example.core.creeper.loadtask.DouyuRecordLoadTask;
import org.example.core.manager.annotation.Creeper;

/**
 * @author Genius
 * @date 2023/07/28 23:17
 **/
@Data
@Creeper(creeperName = "douyu_live",loadTask = DouyuLiveOnlineLoadTask.class,creeperDescription = "斗鱼直播爬取")
public class DouyuLiveOnlineConfig extends LoadLiveConfig {

    public DouyuLiveOnlineConfig(String roomId, String videoPath, String videoName,int clarity) {
        super(roomId, videoPath, videoName, false);
        this.clarity = clarity;
        setHeader();

    }

    public DouyuLiveOnlineConfig(String roomId, String videoPath, String videoName,boolean convertToMp4) {
        super(roomId, videoPath, videoName, convertToMp4);
        this.clarity = 4000;

        setHeader();
    }

    private void setHeader(){
        this.url = "https://www.douyu.com/lapi/live/getH5Play/%s?%s&cdn=ws-h5&rate=0";
        this.UserAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36";
        this.Origin = "https://www.douyu.com";
        this.Referer = "https://www.douyu.com/topic/15ZNQ?rid=3357246&dyshid=0-818074ef9c05a3fe94acdfe500091601";
    }
}
