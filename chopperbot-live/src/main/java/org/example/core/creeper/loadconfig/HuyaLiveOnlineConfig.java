package org.example.core.creeper.loadconfig;

import lombok.Data;
import org.example.constpool.ConstGroup;
import org.example.constpool.ConstPool;
import org.example.core.creeper.loadtask.DouyuLiveOnlineLoadTask;
import org.example.core.manager.Creeper;

/**
 * @author dhx
 * @date 2023/10/9 19:41
 */
@Data
@Creeper(creeperName = "虎牙直播爬虫",
        loadTask = DouyuLiveOnlineLoadTask.class,
        creeperDescription = "虎牙直播爬取(包含监控器)",
        priority = 10,
        group = ConstGroup.LIVE_ONLINE,
        platform = ConstPool.HUYA
)
public class HuyaLiveOnlineConfig extends LoadLiveConfig{
    public HuyaLiveOnlineConfig(String roomId, String videoPath, String videoName) {
        super(roomId, videoPath, videoName, false);
        this.platform = ConstPool.PLATFORM.HUYA.getName();
        setHeader();
    }

    public HuyaLiveOnlineConfig(String roomId, String videoPath, String videoName,boolean convertToMp4) {
        super(roomId, videoPath, videoName, convertToMp4);
        this.platform = ConstPool.PLATFORM.HUYA.getName();
        setHeader();
    }
    private void setHeader(){
        this.UserAgent = "Mozilla/5.0 (Linux; Android 5.0; SM-G900P Build/LRX21T) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Mobile Safari/537.36 ";
    }
}
