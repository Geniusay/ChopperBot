package org.example.api;

import org.example.bean.HotLive;
import org.example.bean.hotmodule.HotModuleList;
import org.example.core.control.hotmodule.DouyuHotLiveLoadTask;
import org.example.core.control.hotmodule.DouyuHotModuleLoadTask;

import java.util.List;

/**
 * @author Genius
 * @date 2023/07/21 17:53
 **/
public class HotModuleApi {
    private static DouyuHotModuleLoadTask douyuHotModuleLoadTask = new DouyuHotModuleLoadTask();
    private static DouyuHotLiveLoadTask douyuHotLiveLoadTask = new DouyuHotLiveLoadTask();

    public static HotModuleList getDouyuAllHotModule(){
        return douyuHotModuleLoadTask.start();
    }

    public static List<HotLive> getDouyuHotLive(){
        return douyuHotLiveLoadTask.start();
    }

    public static List<HotLive> getDouyuHotLive(int moduleId){
        return douyuHotLiveLoadTask.start(moduleId);
    }
}
