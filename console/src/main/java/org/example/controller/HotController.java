package org.example.controller;

import com.genius.assistant.common.Result;
import org.example.api.HotModuleApi;
import org.example.bean.Live;
import org.example.bean.HotModule;
import org.example.bean.hotmodule.HotModuleList;
import org.example.constpool.ConstPool;
import org.example.core.HotModuleDataCenter;
import org.example.service.HotModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/douyu/getHotModuleLives")
    public Result getDouyuHotModuleLives(@RequestParam int moduleId){
        HotModule moduleHotLives = hotModuleService.getModuleHotLives(ConstPool.PLATFORM.DOUYU.getName(), moduleId);
        return Result.success(moduleHotLives);
    }
}
