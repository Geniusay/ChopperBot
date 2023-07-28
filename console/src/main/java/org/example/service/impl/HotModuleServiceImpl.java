package org.example.service.impl;

import org.example.bean.Live;
import org.example.bean.HotModule;
import org.example.bean.hotmodule.HotModuleList;
import org.example.core.HotModuleDataCenter;
import org.example.service.HotModuleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Genius
 * @date 2023/07/21 17:23
 **/

@Service
public class HotModuleServiceImpl implements HotModuleService {

    /**
     * 获得热门模块下的热门直播
     * @param moduleId
     * @return
     */
    @Override
    public HotModule getModuleHotLives(String platform,int moduleId) {
        HotModuleList moduleList = HotModuleDataCenter.DataCenter().getModuleList(platform);
        if(moduleList==null){
            return null;
        }
        HotModule hotModule = moduleList.findHotModule(moduleId);
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
