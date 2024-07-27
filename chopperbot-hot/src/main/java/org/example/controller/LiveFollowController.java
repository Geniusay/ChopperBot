package org.example.controller;


import com.genius.assistant.common.Result;
import org.example.api.LiverFollowApi;
import org.example.bean.FocusLiver;
import org.example.constpool.PluginName;
import org.example.plugin.annotation.CheckPlugin;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hot/liveFollow")
@CheckPlugin(needPlugin = {PluginName.HOT_LIVER_FOLLOWER})
public class LiveFollowController {

    @Resource
    private LiverFollowApi liverFollowApi;

    @GetMapping("/list")
    public Result followList(){
        List<FocusLiver> focusLivers = liverFollowApi.allFocusLivers();
        return Result.success(
                Map.of("list",focusLivers)
        );
    }

    @PostMapping("/add")
    public Result addFocus(@RequestBody FocusLiver focusLiver){
        boolean follow = liverFollowApi.follow(focusLiver);
        return Result.success(
                Map.of("success",follow)
        );
    }

    @GetMapping("/delete")
    public Result deleteFocus(@RequestParam String platform,@RequestParam String liver){
        boolean delete = liverFollowApi.unFollow(platform,liver);
        return Result.success(
                Map.of("success",delete)
        );
    }

    @GetMapping("/changeSetting")
    public Result changeLiverFollowSetting(@RequestParam(required = false) Integer focusLive,
                                           @RequestParam(required = false) Integer focusBarrage,
                                           @RequestParam(required = false) Long checkTime){
        liverFollowApi.changeSetting(
                Map.of("focusLive",focusLive,
                        "focusBarrage",focusBarrage,
                        "checkTime",checkTime
                )
        );
        return Result.success(
                Map.of("success",true)
        );
    }

    @GetMapping("/setting")
    public Result changeLiverFollowSetting(){
        return Result.success(
                Map.of("setting",liverFollowApi.getSetting())
        );
    }
}
