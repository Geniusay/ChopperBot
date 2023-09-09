package org.example.core.creeper.loadconfig;

import lombok.Data;
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
        group = "bilibili_focus_check"
)
public class BilibiliLiverCheckerConfig extends LoadConfig {

    private String liver;

    public BilibiliLiverCheckerConfig(String liver){
        this.liver = liver;
        this.url = String.format("https://api.bilibili.com/x/web-interface/wbi/search/type?order=online&keyword=%s&search_type=live",liver);
        this.cookie = new HashMap<>();
        this.cookie.put("buvid3","CA14E612-862F-CE61-28CD-6B3203B41C6D01288infoc");
    }
}
