package org.example.controller;

import com.genius.assistant.common.Result;
import org.example.api.HotModuleApi;
import org.example.bean.FocusLiver;
import org.example.bean.Live;
import org.example.bean.HotModule;
import org.example.bean.hotmodule.HotModuleList;
import org.example.constpool.ConstPool;
import org.example.constpool.PluginName;
import org.example.core.HotModuleDataCenter;
import org.example.plugin.annotation.CheckPlugin;
import org.example.service.FocusLiverService;
import org.example.service.HotModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Genius
 * @date 2023/07/21 17:13
 **/
@RestController
@RequestMapping("/hot")
public class HotController {

    @Autowired
    HotModuleService hotModuleService;

    @CheckPlugin(needPlugin = {PluginName.HOT_GUARD_PLUGIN})
    @GetMapping("/douyu/allHotLive")
    public Result getDouyuAllHotLive(@RequestParam(defaultValue = "0") int latest){
        List<? extends Live> lives;
        if(latest==1){
            lives = HotModuleApi.getDouyuHotLive();
        }else{
            lives = HotModuleDataCenter.DataCenter().getLiveList(ConstPool.PLATFORM.DOUYU.getName());
        }
        return Result.success(lives);
    }

    @CheckPlugin(needPlugin = {PluginName.HOT_GUARD_PLUGIN})
    @GetMapping("/douyu/allHotModule")
    public Result getDouyuAllHotModule(@RequestParam(defaultValue = "0") int latest){
        HotModuleList hotModuleList;
        if(latest==1){
            hotModuleList = HotModuleApi.getDouyuAllHotModule();
        }else{
            hotModuleList = HotModuleDataCenter.DataCenter().getModuleList(ConstPool.PLATFORM.DOUYU.getName());
        }
        return Result.success(hotModuleList.getHotModuleList());
    }

    @CheckPlugin(needPlugin = {PluginName.HOT_GUARD_PLUGIN})
    @GetMapping("/douyu/getHotModuleLives")
    public Result getDouyuHotModuleLives(@RequestParam int moduleId){
        HotModule moduleHotLives = hotModuleService.getModuleHotLives(ConstPool.PLATFORM.DOUYU.getName(), moduleId);
        return Result.success(moduleHotLives);
    }

    @Resource
    FocusLiverService service;

    @GetMapping("/insert")
    public boolean insert(){
        FocusLiver focusLiver = new FocusLiver(0, "Asaki大人", "6154037", ConstPool.BILIBILI, "独立游戏");
        return service.addLivers(focusLiver);
    }
}
