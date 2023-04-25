package org.example.core.factory;

import org.example.core.control.LoadTask;
import org.example.core.control.impl.LoadTask_L_Bilibili;
import org.example.core.control.impl.LoadTask_R_Douyu;
import org.example.pojo.download.LoadConfig;
import org.example.pojo.download.assign.LoadConfig_L_Bilibili;
import org.example.pojo.download.assign.LoadConfig_R_Douyu;

/**
 * 弹幕下载任务工厂
 * @author 燧枫
 * @date 2023/4/23 19:36
*/
public class TaskFactory {

    /**
     * 通过配置信息来获取一个任务
     * @param loadConfig
     * @return LoadTask
     */
    public LoadTask getLoadTask(LoadConfig loadConfig) {

        if (loadConfig == null) {
            return null;
        }

        // 斗鱼录播
        if (loadConfig instanceof LoadConfig_R_Douyu) {
            return new LoadTask_R_Douyu((LoadConfig_R_Douyu) loadConfig);
        }
        // B站直播
        else if (loadConfig instanceof LoadConfig_L_Bilibili) {
            return new LoadTask_L_Bilibili((LoadConfig_L_Bilibili) loadConfig);
        }

        return null;
    }
}
