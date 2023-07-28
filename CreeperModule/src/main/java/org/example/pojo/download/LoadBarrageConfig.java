package org.example.pojo.download;

import lombok.Data;
import org.example.utils.FormatUtil;

/**
 * 单次弹幕爬取信息配置基类
 * @author 燧枫
 * @date 2023/4/23 16:03
*/
public class LoadBarrageConfig extends LoadConfig{

    // 爬取的平台
    protected String platform;

    // 爬取的是录播还是直播
    protected String action;

    // 主播名称
    protected String anchorName;

    // 开始时间

    public LoadBarrageConfig(String platform, String action, String anchorName) {
        this.platform = platform;
        this.action = action;
        this.anchorName = anchorName;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAnchorName() {
        return anchorName;
    }

    public void setAnchorName(String anchorName) {
        this.anchorName = anchorName;
    }
}
