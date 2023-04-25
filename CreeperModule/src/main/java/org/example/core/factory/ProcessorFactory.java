package org.example.core.factory;

import org.example.core.processor.AbstractProcessor;
import org.example.core.processor.Process_L_Bilibili;
import org.example.core.processor.Process_R_Douyu;
import org.example.pojo.download.LoadConfig;
import org.example.pojo.download.assign.LoadConfig_L_Bilibili;
import org.example.pojo.download.assign.LoadConfig_R_Douyu;
import org.example.utils.PachongConfig;

/**
 * 处理器工厂
 * @author 燧枫
 * @date 2023/4/23 21:28
 */
public class ProcessorFactory {

    private final String bi_userAgent = PachongConfig.getProperty("dy.userAgent");
    private final int bi_retryTimes = PachongConfig.getIntProperty("dy.retryTimes");
    private final int bi_retrySleepTime = PachongConfig.getIntProperty("dy.retrySleepTime");

    private final String dy_userAgent = PachongConfig.getProperty("bi.userAgent");
    private final int dy_retryTimes = PachongConfig.getIntProperty("bi.retryTimes");
    private final int dy_retrySleepTime = PachongConfig.getIntProperty("bi.retrySleepTime");

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
        if (loadConfig instanceof LoadConfig_R_Douyu) {
            return new Process_R_Douyu((LoadConfig_R_Douyu) loadConfig, dy_retryTimes,
                    dy_retrySleepTime, dy_userAgent);
        }
        // B站直播
        else if (loadConfig instanceof LoadConfig_L_Bilibili) {
            return new Process_L_Bilibili((LoadConfig_L_Bilibili) loadConfig, bi_retryTimes,
                    bi_retrySleepTime,bi_userAgent);
        }

        return null;
    }
}
