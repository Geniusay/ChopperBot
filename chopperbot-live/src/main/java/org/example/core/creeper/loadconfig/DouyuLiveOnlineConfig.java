package org.example.core.creeper.loadconfig;

import lombok.Data;
import org.example.constpool.ConstGroup;
import org.example.constpool.ConstPool;
import org.example.core.creeper.loadtask.DouyuLiveOnlineLoadTask;
import org.example.core.manager.Creeper;

/**
 * @author Genius
 * @date 2023/07/28 23:17
 **/
@Data
@Creeper(creeperName = "斗鱼直播爬虫",
        loadTask = DouyuLiveOnlineLoadTask.class,
        creeperDescription = "斗鱼直播爬取(包含监控器)",
        priority = 10,
        group = ConstGroup.LIVE_ONLINE,
        platform = ConstPool.DOUYU
)
public class DouyuLiveOnlineConfig extends LoadLiveConfig {
    public DouyuLiveOnlineConfig(String roomId, String videoPath, String videoName,int clarity) {
        super(roomId, videoPath, videoName, false);
        this.platform = ConstPool.PLATFORM.DOUYU.getName();
        setHeader(clarity);
    }
    /**
     清晰度clarity：0-3
     0画质最高
     */
    public DouyuLiveOnlineConfig(String roomId, String videoPath, String videoName,boolean convertToMp4,int clarity) {
        super(roomId, videoPath, videoName, convertToMp4);
        this.platform = ConstPool.PLATFORM.DOUYU.getName();
        setHeader(clarity);
    }

    public DouyuLiveOnlineConfig(String roomId, String videoPath, String videoName,boolean convertToMp4) {
        super(roomId, videoPath, videoName, convertToMp4);
        this.platform = ConstPool.PLATFORM.DOUYU.getName();
        setHeader(0);


    }

    private void setHeader(int clarity){
        this.url = "https://www.douyu.com/lapi/live/getH5Play/%s?%s&cdn=ws-h5&rate="+clarity;
        this.UserAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36";
        this.Origin = "https://www.douyu.com";
        this.Referer = "https://www.douyu.com/topic/15ZNQ?rid=3357246&dyshid=0-818074ef9c05a3fe94acdfe500091601";
    }
}
