package org.example.core.creeper.loadconfig;

import lombok.Data;
import org.example.core.loadconfig.LoadConfig;
import org.example.util.TimeUtil;

/**
 * 单次弹幕爬取信息配置基类
 * @author 燧枫
 * @date 2023/4/23 16:03
*/
@Data
public class LoadBarrageConfig extends LoadConfig {

    // 爬取的平台
    protected String platform;

    // 爬取的是录播还是直播
    protected String action;

    // 主播名称
    protected String anchorName;

    public LoadBarrageConfig(String platform, String action, String anchorName) {
        super();
        this.platform = platform;
        this.action = action;
        this.anchorName = anchorName;
    }

    @Override
    public String getTaskId() {
        return action+"_barrage_"+platform+"_"+anchorName+"_"+TimeUtil.getToday_YMD();
    }
}
