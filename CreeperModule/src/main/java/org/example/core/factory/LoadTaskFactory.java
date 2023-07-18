package org.example.core.factory;

import org.example.core.control.LoadTask;
import org.example.core.control.impl.BilibiliLiveLoadTask;
import org.example.core.control.impl.DouyuRecordLoadTask;
import org.example.exception.FileCacheException;
import org.example.pojo.download.LoadBarrageConfig;
import org.example.pojo.download.assign.BilibiliLiveLoadBarrageConfig;
import org.example.pojo.download.assign.DouyuRecordLoadBarrageConfig;

/**
 * 弹幕下载任务工厂
 * @author 燧枫
 * @date 2023/4/23 19:36
*/
public class LoadTaskFactory {

    /**
     * 通过配置信息来获取一个任务
     * @param loadBarrageConfig
     * @return LoadTask
     */
    public LoadTask getLoadTask(LoadBarrageConfig loadBarrageConfig) throws FileCacheException {

        if (loadBarrageConfig == null) {
            return null;
        }

        // 斗鱼录播
        if (loadBarrageConfig instanceof DouyuRecordLoadBarrageConfig) {
            return new DouyuRecordLoadTask((DouyuRecordLoadBarrageConfig) loadBarrageConfig);
        }
        // B站直播
        else if (loadBarrageConfig instanceof BilibiliLiveLoadBarrageConfig) {
            return new BilibiliLiveLoadTask((BilibiliLiveLoadBarrageConfig) loadBarrageConfig);
        }

        return null;
    }
}
