package org.example.controller;

import com.genius.assistant.common.Result;
import org.example.api.HotModuleApi;
import org.example.bean.HotModule;
import org.example.bean.Live;
import org.example.bean.hotmodule.HotModuleList;
import org.example.constpool.PluginName;
import org.example.plugin.annotation.CheckPlugin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 热门模块插件接口
 */
@RestController
@RequestMapping("/hot/hotLive")
@CheckPlugin(needPlugin = {PluginName.HOT_GUARD_PLUGIN})
public class HotModuleController {

    @Resource
    HotModuleApi hotModuleApi;

    @GetMapping("/live")
    public Result getAllHotLive(@RequestParam(defaultValue = "0") int latest, @RequestParam String platform){
        List<? extends Live> lives = hotModuleApi.getHotLiveList(platform);
        if(lives==null){
            return Result.error("403","暂无该数据");
        }
        return Result.success(Map.of("list",lives));
    }

    @GetMapping("/module")
    public Result getAllHotModule(@RequestParam(defaultValue = "0") int latest,@RequestParam String platform){
        HotModuleList hotModuleList = hotModuleApi.getAllHotModule(platform);
        if(hotModuleList==null||hotModuleList.getHotModuleList()==null){
            return Result.error("403","暂无该数据");
        }
        return Result.success(Map.of("list",hotModuleList.getHotModuleList()));
    }

    @GetMapping("/modelLive")
    public Result getHotModuleLives(@RequestParam String moduleId,@RequestParam String platform){
        HotModule moduleHotLives = hotModuleApi.getModuleList(platform, moduleId);
        if(moduleHotLives==null||moduleHotLives.getHotLives()==null){
            return Result.error("403","暂无该数据");
        }
        return Result.success(Map.of("list",moduleHotLives.getHotLives()));
    }
}
