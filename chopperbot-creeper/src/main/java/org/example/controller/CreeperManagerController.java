package org.example.controller;


import com.genius.assistant.common.Result;
import org.example.api.CreeperManagerApi;
import org.example.constpool.PluginName;
import org.example.core.manager.CreeperBean;
import org.example.plugin.annotation.CheckPlugin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/creeper/creeperManager")
public class CreeperManagerController {

    @Resource
    private CreeperManagerApi creeperManagerApi;

    @CheckPlugin(needPlugin = {PluginName.CREEPER_MANAGER_PLUGIN})
    @GetMapping("/getAllCreeper")
    public Result getAllCreeper(){
        List<CreeperBean> allCreeper = creeperManagerApi.getAllCreeper();
        return Result.success(Map.of(
                "list",allCreeper
        ));
    }
}
