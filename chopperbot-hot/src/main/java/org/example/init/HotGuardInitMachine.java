package org.example.init;

import org.example.constpool.ConstPool;
import org.example.constpool.ModuleName;
import org.example.constpool.PluginName;
import org.example.core.guard.HotModuleGuard;
import org.example.plugin.CommonPlugin;
import org.example.plugin.annotation.Plugin;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Genius
 * @date 2023/07/21 09:58
 **/

@Plugin(moduleName = ModuleName.HOT,
        pluginName = PluginName.HOT_GUARD_PLUGIN,
        pluginName_CN = "平台热门监控插件",
        pluginDescription = "实时监控各个平台的最新直播",
        needPlugin = {PluginName.FILE_CACHE_PLUGIN,PluginName.HOT_CONFIG_PLUGIN,PluginName.CREEPER_MANAGER_PLUGIN},
        pluginClass= HotModuleGuard.class,
        springBootPlugin = true
)
@Component
public class HotGuardInitMachine extends SpringPlugInitMachine{

}
