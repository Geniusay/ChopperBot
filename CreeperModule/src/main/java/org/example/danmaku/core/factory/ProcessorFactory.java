package org.example.danmaku.core.factory;

import org.example.danmaku.core.processor.AbstractProcessor;
import org.example.danmaku.core.processor.BilibiliLiveProcessor;
import org.example.danmaku.core.processor.DouyuRecordProcessor;
import org.example.danmaku.pojo.download.LoadConfig;
import org.example.danmaku.pojo.download.assign.BilibiliLiveLoadConfig;
import org.example.danmaku.pojo.download.assign.DouyuRecordLoadConfig;
import org.example.danmaku.utils.CreeperConfig;

/**
 * 处理器工厂
 *
 * @author 燧枫
 * @date 2023/4/23 21:28
 */
public class ProcessorFactory {

    private final String bi_userAgent = CreeperConfig.getProperty("dy.userAgent");
    private final int bi_retryTimes = CreeperConfig.getIntProperty("dy.retryTimes");
    private final int bi_retrySleepTime = CreeperConfig.getIntProperty("dy.retrySleepTime");
    private final int bi_sleepTime = CreeperConfig.getIntProperty("dy.sleepTime");

    private final String dy_userAgent = CreeperConfig.getProperty("bi.userAgent");
    private final int dy_retryTimes = CreeperConfig.getIntProperty("bi.retryTimes");
    private final int dy_retrySleepTime = CreeperConfig.getIntProperty("bi.retrySleepTime");
    private final int dy_sleepTime = CreeperConfig.getIntProperty("bi.sleepTime");

    /**
     * 通过配置信息来获取一个处理器
     *
     * @param loadConfig
     * @return AbstractProcessor
     */
    public AbstractProcessor getProcessor(LoadConfig loadConfig) {

        if (loadConfig == null) {
            return null;
        }

        // 斗鱼录播
        if (loadConfig instanceof DouyuRecordLoadConfig) {
            return new DouyuRecordProcessor((DouyuRecordLoadConfig) loadConfig, dy_retryTimes,
                    dy_retrySleepTime, dy_userAgent, dy_sleepTime);
        }
        // B站直播
        else if (loadConfig instanceof BilibiliLiveLoadConfig) {
            return new BilibiliLiveProcessor((BilibiliLiveLoadConfig) loadConfig, bi_retryTimes,
                    bi_retrySleepTime, bi_userAgent, bi_sleepTime);
        }

        return null;
    }
}
