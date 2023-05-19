package org.example.danmaku.pojo.download;

import lombok.Data;
import org.example.danmaku.utils.FormatUtil;

/**
 * 单次弹幕爬取信息配置基类
 * @author 燧枫
 * @date 2023/4/23 16:03
*/
@Data
public class LoadConfig {

    // 爬取的平台
    protected String platform;

    // 爬取的是录播还是直播
    protected String action;

    // 主播名称
    protected String anchorName;

    // 开始时间
    protected String startTime;

    public LoadConfig(String platform, String action, String anchorName) {
        this.platform = platform;
        this.action = action;
        this.anchorName = anchorName;
        this.startTime = FormatUtil.getNowDate();
    }
}
