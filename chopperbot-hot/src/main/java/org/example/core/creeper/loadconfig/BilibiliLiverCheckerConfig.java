package org.example.core.creeper.loadconfig;

import lombok.Data;
import org.example.constpool.ConstGroup;
import org.example.constpool.ConstPool;
import org.example.core.creeper.loadtask.BilibiliLiverCheckerLoadTask;
import org.example.core.creeper.loadtask.DouyuHotLiveLoadTask;
import org.example.core.loadconfig.LoadConfig;
import org.example.core.manager.Creeper;

import java.util.HashMap;

/**
 * @author Genius
 * @date 2023/09/10 01:03
 **/
@Data
@Creeper(creeperName = "B站直播检测爬虫",
        loadTask = BilibiliLiverCheckerLoadTask.class,
        creeperDescription = "用于检测B站主播是否开播，并且获取直播详细信息",
        priority = 10,
        group = ConstGroup.LIVER_CHECKER,
        platform = ConstPool.BILIBILI
)
public class BilibiliLiverCheckerConfig extends LoadConfig {

    private String liver;

    public BilibiliLiverCheckerConfig(String liver){
        this.liver = liver;
        this.url = String.format("https://api.bilibili.com/x/web-interface/wbi/search/type?order=online&keyword=%s&search_type=live",liver);
        this.cookie = new HashMap<>();
        this.cookie.put("buvid3","6BA6EB5A-E197-4F47-A2C4-E4022E98BC85138387infoc");
        this.cookie.put("SESSDATA","225349b3%2C1710488625%2C80bab%2A91CjA-rMVpeZ4FXvvQSFSrP4D5V7OgxWQcyj6nBD0hu574u6Ri95MNSdhT07KZxVFYC-ISVkdzbWpxR3ZJSFFKekhqU0dLOGNDTWY1UVZudlJzQ3RROGpfYTVoWHEwVDg2V1lac1ZQc0NyblNJaDhyWW1DZmdyTkpxMmZJdTVHYXlxXy1wZ3ZhdTh3IIEC");
    }
}
