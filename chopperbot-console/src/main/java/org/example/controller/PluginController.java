package org.example.controller;

import org.example.api.PluginApi;
import org.example.bean.Plugin;
import org.example.constpool.GlobalFileCache;
import org.example.constpool.ModuleName;
import org.example.constpool.PluginName;
import org.example.init.InitPluginRegister;
import org.example.service.PluginService;
import org.example.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Genius
 * @date 2023/08/01 23:39
 **/
@RestController
@RequestMapping("/plugin")
public class PluginController {

    @Resource
    PluginService pluginService;


    @GetMapping("/get")
    public Result getPlugin(){
        return Result.success(pluginService.getPlugins(null));
    }

    @GetMapping("/get/{moduleName}")
    public Result getPlugin(@PathVariable(required = false) String moduleName){
        return Result.success(pluginService.getPlugins(moduleName));
    }

    @GetMapping("/close")
    public Result closePlugin(@RequestParam("plugin")String plugin){
        if(plugin.equals(PluginName.CHOPPER_BOT_CONFIG_PLUGIN)){
            return Result.error("System plugin cannot close");
        }
        boolean res = InitPluginRegister.closePlugin(plugin);
        if(!res){
            return Result.error(plugin+" not register");
        }
        return Result.success(Map.of("plugin",plugin));
    }

    @GetMapping("/start")
    public Result startPlugin(@RequestParam("plugin")String plugin){
        if (InitPluginRegister.startPlugin(plugin)) {
            return Result.success(Map.of("plugin",plugin));
        }
        return Result.error("The plugin has been started or does not exist");
    }

    @GetMapping("/switchAutoStart")
    public Result switchAutoStart(@RequestParam("plugin")String plugin,@RequestParam("isOpen")Boolean isOpen){
        boolean b = pluginService.pluginApi().switchPluginAutoStart(plugin, isOpen);
        return Result.success(Map.of(
                "success",b
        ));
    }
}
