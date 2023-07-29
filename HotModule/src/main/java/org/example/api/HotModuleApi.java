package org.example.api;

import org.example.bean.Live;
import org.example.bean.hotmodule.DouyuLive;
import org.example.bean.hotmodule.HotModuleList;
import org.example.core.control.impl.DouyuHotLiveLoadTask;
import org.example.core.control.impl.DouyuHotModuleLoadTask;
import org.example.core.factory.LoadTaskFactory;
import org.example.pojo.download.assign.DouyuHotLiveConfig;
import org.example.pojo.download.assign.DouyuHotModuleConfig;

import java.util.List;

/**
 * 热门模块的一些爬虫方法api实现
 * @author Genius
 * @date 2023/07/21 17:53
 **/
public class HotModuleApi {


    public static HotModuleList getDouyuAllHotModule(){
        return (HotModuleList)LoadTaskFactory.getLoadTask(new DouyuHotModuleConfig()).start();
    }

    public static List<DouyuLive> getDouyuHotLive(){
        return  ((DouyuHotLiveLoadTask)LoadTaskFactory.getLoadTask(new DouyuHotLiveConfig())).start();
    }

    public static List<DouyuLive> getDouyuHotLive(int moduleId){
        return ((DouyuHotLiveLoadTask)LoadTaskFactory.getLoadTask(new DouyuHotLiveConfig())).start(moduleId);
    }
}
