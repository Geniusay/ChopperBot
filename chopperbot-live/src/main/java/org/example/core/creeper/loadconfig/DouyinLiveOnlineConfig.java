package org.example.core.creeper.loadconfig;

import lombok.Data;
import org.example.constpool.ConstGroup;
import org.example.constpool.ConstPool;
import org.example.core.creeper.loadtask.DouyinLiveOnlineLoadTask;
import org.example.core.manager.Creeper;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dhx
 * @date 2024/5/19 15:33
 */
@Data
@Creeper(creeperName = "抖音直播爬虫",
        loadTask = DouyinLiveOnlineLoadTask.class,
        creeperDescription = "抖音直播爬取(包含监控器)",
        priority = 10,
        group = ConstGroup.LIVE_ONLINE,
        platform = ConstPool.DOUYIN
)
public class DouyinLiveOnlineConfig extends LoadLiveConfig {
    public DouyinLiveOnlineConfig(String roomId, String videoPath, String videoName,int clarity) {
        super(roomId, videoPath, videoName, false);
        this.platform = ConstPool.PLATFORM.DOUYIN.getName();
        setHeader();
    }

    public DouyinLiveOnlineConfig(String roomId, String videoPath, String videoName,boolean convertToMp4,int clarity) {
        super(roomId, videoPath, videoName, convertToMp4);
        this.platform = ConstPool.PLATFORM.DOUYIN.getName();
        setHeader();
    }

    public DouyinLiveOnlineConfig(String roomId, String videoPath, String videoName,boolean convertToMp4) {
        super(roomId, videoPath, videoName, convertToMp4);
        this.platform = ConstPool.PLATFORM.DOUYIN.getName();
        setHeader();
    }

    private void setHeader(){
        this.url = "https://live.douyin.com/webcast/room/web/enter/?aid=6383&app_name=douyin_web&live_id=1&device_platform=web&language=zh-CN&enter_from=web_live&cookie_enabled=true&screen_width=1728&screen_height=1117&browser_language=zh-CN&browser_platform=MacIntel&browser_name=Chrome&browser_version=116.0.0.0&web_rid=";
        this.UserAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36";
        this.Origin = "https://live.douyin.com";
        this.Referer = "https://live.douyin.com";
        Map<String,String> dyHeader = new HashMap<>();
        dyHeader.put("Upgrade-Insecure-Requests","1");
        dyHeader.put("Accept","*/*");
        dyHeader.put("Host","live.douyin.com");
        dyHeader.put("Connection","keep-alive");
        this.header = dyHeader;
    }
}
