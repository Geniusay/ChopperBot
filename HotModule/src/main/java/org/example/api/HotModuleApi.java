package org.example.api;

import org.example.bean.live.DouyuLive;
import org.example.bean.hotmodule.HotModuleList;

import org.example.core.creeper.loadconfig.DouyuHotLiveConfig;
import org.example.core.creeper.loadconfig.DouyuHotModuleConfig;
import org.example.core.creeper.loadtask.DouyuHotLiveLoadTask;
import org.example.core.creeper.loadtask.DouyuHotModuleLoadTask;


import java.util.List;

/**
 * 热门模块的一些爬虫方法api实现
 * @author Genius
 * @date 2023/07/21 17:53
 **/
//TODO 待重构
public class HotModuleApi {



    public static HotModuleList getDouyuAllHotModule(){
        return new DouyuHotModuleLoadTask(new DouyuHotModuleConfig()).start();
    }

    public static List<DouyuLive> getDouyuHotLive(){
        return new DouyuHotLiveLoadTask(new DouyuHotLiveConfig()).start();
    }

    public static List<DouyuLive> getDouyuHotLive(int moduleId){
        return new DouyuHotLiveLoadTask(new DouyuHotLiveConfig(moduleId)).start();
    }
}
