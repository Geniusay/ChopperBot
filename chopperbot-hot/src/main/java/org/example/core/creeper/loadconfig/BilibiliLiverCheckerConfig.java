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
        this.cookie.put("SESSDATA","9481d398%2C1711296621%2C4d8e5%2A91CjB4IXMIVvMxEG4t5eeq6jicEzEbBHPyKRAJcBNLSi7n_-bBq8ik86Cua7JRupYgqPoSVl9pM2tHYllWSURiQmxESDByUExXU0kwV01JckN0dlB4Sks4aHk5V1FTOXBsSG9STkVJNnhwakxoS2VscWV6a0taYm0wa2xPVUxMRk9lZ2R5T19mS3FBIIEC");
    }
}
