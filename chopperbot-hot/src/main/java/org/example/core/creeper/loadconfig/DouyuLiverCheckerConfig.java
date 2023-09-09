package org.example.core.creeper.loadconfig;

import lombok.Data;
import org.example.core.creeper.loadtask.DouyuHotLiveLoadTask;
import org.example.core.creeper.loadtask.DouyuLiverCheckerLoadTask;
import org.example.core.loadconfig.LoadConfig;
import org.example.core.manager.Creeper;

/**
 * @author Genius
 * @date 2023/09/10 01:55
 **/
@Data
@Creeper(creeperName = "斗鱼直播检测爬虫",
        loadTask = DouyuLiverCheckerLoadTask.class,
        creeperDescription = "用于检测斗鱼主播是否开播，并且获取直播详细信息",
        priority = 10,
        group = "douyu_focus_check"
)
public class DouyuLiverCheckerConfig extends LoadConfig {
    private String roomId;

    public DouyuLiverCheckerConfig(String roomId) {
        this.roomId = roomId;
        this.url = "https://www.douyu.com/betard/"+roomId;
    }
}
