package org.example.controller;

import com.genius.assistant.common.Result;
import org.example.api.HotModuleApi;
import org.example.bean.*;
import org.example.bean.hotmodule.HotModuleList;
import org.example.constpool.ConstPool;
import org.example.constpool.PluginName;
import org.example.core.HotModuleDataCenter;
import org.example.core.guard.Guard;
import org.example.plugin.annotation.CheckPlugin;
import org.example.service.FocusLiverService;
import org.example.service.HotModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
    @GetMapping("/hotLive/live")
    public Result getDouyuAllHotLive(@RequestParam(defaultValue = "0") int latest,@RequestParam String platform){
        List<? extends Live> lives;
        if(latest==1){
            lives = HotModuleApi.getDouyuHotLive();
        }else{
            lives = HotModuleDataCenter.DataCenter().getLiveList(platform);
        }
        return Result.success(lives);
    }

    @CheckPlugin(needPlugin = {PluginName.HOT_GUARD_PLUGIN})
    @GetMapping("/hotLive/module")
    public Result getDouyuAllHotModule(@RequestParam(defaultValue = "0") int latest,@RequestParam String platform){
        HotModuleList hotModuleList;
        if(latest==1){
            hotModuleList = HotModuleApi.getDouyuAllHotModule();
        }else{
            hotModuleList = HotModuleDataCenter.DataCenter().getModuleList(platform);
        }
        return Result.success(hotModuleList.getHotModuleList());
    }

    @CheckPlugin(needPlugin = {PluginName.HOT_GUARD_PLUGIN})
    @GetMapping("/hotLive/modelLive")
    public Result getDouyuHotModuleLives(@RequestParam int moduleId,@RequestParam String platform){
        HotModule moduleHotLives = hotModuleService.getModuleHotLives(platform, moduleId);
        return Result.success(moduleHotLives);
    }

    @CheckPlugin(needPlugin = {PluginName.HOT_GUARD_PLUGIN})
    @GetMapping("/hotGuard/setting")
    public Result getHotGuardSetting(){
        List<HotModuleSetting> allSettings = hotModuleService.hotModuleGuardApi().getAllSettings();
        return Result.success(
                Map.of("list",allSettings)
        );
    }

    @CheckPlugin(needPlugin = {PluginName.HOT_GUARD_PLUGIN})
    @PostMapping("/hotGuard/update")
    public Result updateHotGuardSetting(@RequestBody HotModuleSetting setting){
        boolean b = hotModuleService.hotModuleGuardApi().changeSetting(setting);
        return Result.success(
                Map.of("success",b)
        );
    }

    @CheckPlugin(needPlugin = {PluginName.HOT_GUARD_PLUGIN})
    @GetMapping("/hotGuard/guard")
    public Result hotGuards(){
        List<GuardVO> guards = hotModuleService.hotModuleGuardApi().getGuards();
        return Result.success(
                Map.of("list",guards)
        );
    }

    @CheckPlugin(needPlugin = {PluginName.HOT_LIVER_FOLLOWER})
    @GetMapping("/liveFollow/list")
    public Result followList(){
        List<FocusLiver> focusLivers = hotModuleService.liverFollowApi().allFocusLivers();
        return Result.success(
                Map.of("list",focusLivers)
        );
    }

    @CheckPlugin(needPlugin = {PluginName.HOT_LIVER_FOLLOWER})
    @PostMapping("/liveFollow/add")
    public Result addFocus(@RequestBody FocusLiver focusLiver){
        boolean follow = hotModuleService.liverFollowApi().follow(focusLiver);
        return Result.success(
                Map.of("success",follow)
        );
    }

    @CheckPlugin(needPlugin = {PluginName.HOT_LIVER_FOLLOWER})
    @GetMapping("/liveFollow/delete")
    public Result deleteFocus(@RequestParam String platform,@RequestParam String liver){
        boolean delete = hotModuleService.liverFollowApi().unFollow(platform,liver);
        return Result.success(
                Map.of("success",delete)
        );
    }

    @CheckPlugin(needPlugin = {PluginName.HOT_LIVER_FOLLOWER})
    @GetMapping("/liveFollow/changeSetting")
    public Result changeLiverFollowSetting(@RequestParam(required = false) Integer focusLive,
                                           @RequestParam(required = false) Integer focusBarrage,
                                           @RequestParam(required = false) Long checkTime){
        hotModuleService.liverFollowApi().changeSetting(
                Map.of("focusLive",focusLive,
                        "focusBarrage",focusBarrage,
                        "checkTime",checkTime
                )
        );
        return Result.success(
                Map.of("success",true)
        );
    }

    @CheckPlugin(needPlugin = {PluginName.HOT_LIVER_FOLLOWER})
    @GetMapping("/liveFollow/setting")
    public Result changeLiverFollowSetting(){
        return Result.success(
                Map.of("setting",hotModuleService.liverFollowApi().getSetting())
        );
    }

    @CheckPlugin(needPlugin = {PluginName.HOT_RECOMMENDATION_PLUGIN})
    @GetMapping("/hotRecommendation/list")
    public Result getFollowDogs(){
        return Result.success(
                Map.of(
                        "list",hotModuleService.heatRecommendApi().getFollowDog()
                )
        );
    }
    @CheckPlugin(needPlugin = {PluginName.HOT_RECOMMENDATION_PLUGIN})
    @PostMapping("/hotRecommendation/add")
    public Result addFollowDogs(@RequestBody FollowDog dog){
        dog.setId(null);

        boolean success = hotModuleService.heatRecommendApi().addFollowDog(dog);
        if(success){
            return Result.success(
                    Map.of("add",dog)
            );
        }else{
            return Result.error("403","添加失败");
        }
    }



    @CheckPlugin(needPlugin = {PluginName.HOT_RECOMMENDATION_PLUGIN})
    @PostMapping("/hotRecommendation/update")
    public Result updateFollowDogs(@RequestBody FollowDog dog){
        boolean success = hotModuleService.heatRecommendApi().updateFollowDog(dog);
        return Result.success(
                Map.of("success",success)
        );
    }

    @CheckPlugin(needPlugin = {PluginName.HOT_RECOMMENDATION_PLUGIN})
    @GetMapping("/hotRecommendation/delete")
    public Result addFollowDogs(@RequestParam String dogId,@RequestParam String platform){
        boolean success = hotModuleService.heatRecommendApi().deleteFollowDog(dogId,platform);
        return Result.success(
                Map.of("success",success)
        );
    }

    @CheckPlugin(needPlugin = {PluginName.HOT_RECOMMENDATION_PLUGIN})
    @GetMapping("/hotRecommendation/open")
    public Result openFollowDogs(@RequestParam String platform,@RequestParam Boolean isOpen){
        boolean success = hotModuleService.heatRecommendApi().openPlatformFollowDog(platform,isOpen);
        return Result.success(
                Map.of("success",success)
        );
    }
}
