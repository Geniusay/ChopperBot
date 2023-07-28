package org.example.core.factory;

import org.example.core.control.LoadTask;
import org.example.core.control.impl.*;
import org.example.exception.FileCacheException;
import org.example.pojo.download.assign.*;
import org.example.pojo.download.LoadConfig;


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
    public static LoadTask getLoadTask(LoadConfig loadConfig){

        if (loadConfig == null) {
            return null;
        }

        // 斗鱼录播
        if (loadConfig instanceof DouyuRecordLoadBarrageConfig) {
            return new DouyuRecordLoadTask((DouyuRecordLoadBarrageConfig) loadConfig);
        }
        // B站直播
        else if (loadConfig instanceof BilibiliLiveLoadBarrageConfig) {
            return new BilibiliLiveLoadTask((BilibiliLiveLoadBarrageConfig) loadConfig);
        }
        //  斗鱼热门模块
        else if(loadConfig instanceof DouyuHotModuleConfig){
            return new DouyuHotModuleLoadTask();
        //  斗鱼热门直播
        }else if(loadConfig instanceof DouyuHotLiveConfig){
            return new DouyuHotLiveLoadTask();
        }
        // 斗鱼在线直播
        else if(loadConfig instanceof DouyuLiveOnlineConfig){
            return new DouyuLiveOnlineLoadTask((DouyuLiveOnlineConfig) loadConfig);
        }

        return null;
    }
}
