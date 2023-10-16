package org.example.core.creeper.loadconfig;

import lombok.Data;
import org.example.constpool.ConstGroup;
import org.example.constpool.ConstPool;
import org.example.core.creeper.loadtask.HuyaLiverCheckerLoadTask;
import org.example.core.loadconfig.LoadConfig;
import org.example.core.manager.Creeper;

/**
 * @author Genius
 * @date 2023/10/16 23:01
 **/

@Data
@Creeper(creeperName = "虎牙直播检测爬虫",
        loadTask = HuyaLiverCheckerLoadTask.class,
        creeperDescription = "用于检测虎牙主播是否开播，并且获取直播详细信息",
        priority = 10,
        group = ConstGroup.LIVER_CHECKER,
        platform = ConstPool.HUYA
)
public class HuyaLiverCheckerConfig extends LoadConfig {

    private String roomId;

    public HuyaLiverCheckerConfig(String roomId){
        this.roomId = roomId;
        this.url = String.format(String.format("https://search.cdn.huya.com/?m=Search&do=getSearchContent&q=%s&typ=-5&rows=16",  roomId));
    }

    @Override
    public String getTaskId() {
        return super.getTaskId()+"_"+roomId;
    }
}
