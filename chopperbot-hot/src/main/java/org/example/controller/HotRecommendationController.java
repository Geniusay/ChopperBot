package org.example.controller;

import com.genius.assistant.common.Result;
import org.example.api.HeatRecommendApi;
import org.example.bean.FollowDog;
import org.example.constpool.PluginName;
import org.example.plugin.annotation.CheckPlugin;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/hot/hotRecommendation")
@CheckPlugin(needPlugin =PluginName.HOT_RECOMMENDATION_PLUGIN)
public class HotRecommendationController {

    @Resource
    private HeatRecommendApi heatRecommendApi;

    @GetMapping("/list")
    public Result getFollowDogs(){
        return Result.success(
                Map.of(
                        "list",heatRecommendApi.getFollowDog()
                )
        );
    }

    @PostMapping("/add")
    public Result addFollowDogs(@RequestBody FollowDog dog){
        dog.setId(null);

        boolean success = heatRecommendApi.addFollowDog(dog);
        if(success){
            return Result.success(
                    Map.of("add",dog)
            );
        }else{
            return Result.error("403","添加失败");
        }
    }

    @PostMapping("/update")
    public Result updateFollowDogs(@RequestBody FollowDog dog){
        boolean success = heatRecommendApi.updateFollowDog(dog);
        return Result.success(
                Map.of("success",success)
        );
    }

    @GetMapping("/delete")
    public Result addFollowDogs(@RequestParam String dogId, @RequestParam String platform){
        boolean success = heatRecommendApi.deleteFollowDog(dogId,platform);
        return Result.success(
                Map.of("success",success)
        );
    }

    @GetMapping("/open")
    public Result openFollowDogs(@RequestParam String platform,@RequestParam Boolean isOpen){
        boolean success = heatRecommendApi.openPlatformFollowDog(platform,isOpen);
        return Result.success(
                Map.of("success",success)
        );
    }
}
