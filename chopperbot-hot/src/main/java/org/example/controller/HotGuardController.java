package org.example.controller;

import com.genius.assistant.common.Result;
import org.example.api.HotModuleGuardApi;
import org.example.bean.GuardVO;
import org.example.bean.HotModuleSetting;
import org.example.constpool.PluginName;
import org.example.plugin.annotation.CheckPlugin;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 热门守卫插件接口
 */
@RestController
@RequestMapping("/hot/hotGuard")
@CheckPlugin(needPlugin = {PluginName.HOT_GUARD_PLUGIN})
public class HotGuardController {

    @Resource
    private HotModuleGuardApi hotModuleGuardApi;

    @GetMapping("/setting")
    public Result getHotGuardSetting(){
        List<HotModuleSetting> allSettings = hotModuleGuardApi.getAllSettings();
        return Result.success(
                Map.of("list",allSettings)
        );
    }

    @PostMapping("/update")
    public Result updateHotGuardSetting(@RequestBody HotModuleSetting setting){
        boolean b = hotModuleGuardApi.changeSetting(setting);
        return Result.success(
                Map.of("success",b)
        );
    }

    @GetMapping("/guard")
    public Result hotGuards(){
        List<GuardVO> guards = hotModuleGuardApi.getGuards();
        return Result.success(
                Map.of("list",guards)
        );
    }
}
