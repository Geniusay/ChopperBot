package org.example.api;


import org.example.bean.HotModule;
import org.example.bean.Live;
import org.example.bean.live.BiliBiliLive;
import org.example.bean.live.DouyuLive;
import org.example.bean.hotmodule.HotModuleList;
import org.example.core.HotModuleDataCenter;
import org.example.core.creeper.loadconfig.BilibiliHotLiveConfig;
import org.example.core.creeper.loadconfig.DouyuHotLiveConfig;
import org.example.core.creeper.loadconfig.DouyuHotModuleConfig;
import org.example.core.creeper.loadtask.BiliBiliHotLiveLoadTask;
import org.example.core.creeper.loadtask.DouyuHotLiveLoadTask;
import org.example.core.creeper.loadtask.DouyuHotModuleLoadTask;
import org.springframework.stereotype.Component;



import java.util.List;

/**
 * 热门模块的一些爬虫方法api实现
 * @author Genius
 * @date 2023/07/21 17:53
 **/
//TODO 待重构
@Component
public class HotModuleApi {

    public HotModuleList getAllHotModule(String platform){
        return HotModuleDataCenter.DataCenter().getModuleList(platform);
    }

    public List<? extends Live> getHotLiveList(String platform){
       return HotModuleDataCenter.DataCenter().getLiveList(platform);
    }

    public HotModule getModuleList(String platform,String moduleId){
        HotModuleList moduleList = HotModuleDataCenter.DataCenter().getModuleList(platform);
        if(moduleList==null){
            return null;
        }
        HotModule hotModule = moduleList.findHotModuleById(moduleId);
        if(hotModule!=null){
            try {
                List<? extends Live> moduleLiveList = HotModuleDataCenter.DataCenter().getModuleLiveList(platform, hotModule);
                hotModule.setHotLives(moduleLiveList);
                return hotModule;
            }catch (Exception e){
                //TODO 交给Spring全局异常处理器
                return null;
            }
        }
        return null;
    }

}
