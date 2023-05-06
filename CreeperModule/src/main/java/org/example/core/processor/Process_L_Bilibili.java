package org.example.core.processor;

import org.example.pojo.download.assign.LoadConfig_L_Bilibili;
import us.codecraft.webmagic.Page;

/**
 * (B站直播)下载与处理
 * @author 燧枫
 * @date 2023/4/23 18:53
 */
public class Process_L_Bilibili extends AbstractProcessor {

    LoadConfig_L_Bilibili loadConfig;

    public Process_L_Bilibili(LoadConfig_L_Bilibili loadConfig, int retryTimes, int retrySleepTime, String userAgent, int sleepTime) {
        super(retryTimes, retrySleepTime, userAgent, sleepTime);
        this.loadConfig = loadConfig;
    }

    @Override
    public void process(Page page) {
        // 首次加载
        if (isFirst) {
            isFirst = false;
        }
    }
}
