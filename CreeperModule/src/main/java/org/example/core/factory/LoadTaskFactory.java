package org.example.core.factory;

import org.example.core.control.LoadTask;
import org.example.core.control.impl.BilibiliLiveLoadTask;
import org.example.core.control.impl.DouyuRecordLoadTask;
import org.example.exception.FileCacheException;
import org.example.pojo.download.LoadConfig;
import org.example.pojo.download.assign.BilibiliLiveLoadConfig;
import org.example.pojo.download.assign.DouyuRecordLoadConfig;

/**
 * 弹幕下载任务工厂
 * @author 燧枫
 * @date 2023/4/23 19:36
*/
public class LoadTaskFactory {

    /**
     * 通过配置信息来获取一个任务
     * @param loadConfig
     * @return LoadTask
     */
    public LoadTask getLoadTask(LoadConfig loadConfig) throws FileCacheException {

        if (loadConfig == null) {
            return null;
        }

        // 斗鱼录播
        if (loadConfig instanceof DouyuRecordLoadConfig) {
            return new DouyuRecordLoadTask((DouyuRecordLoadConfig) loadConfig);
        }
        // B站直播
        else if (loadConfig instanceof BilibiliLiveLoadConfig) {
            return new BilibiliLiveLoadTask((BilibiliLiveLoadConfig) loadConfig);
        }

        return null;
    }
}
