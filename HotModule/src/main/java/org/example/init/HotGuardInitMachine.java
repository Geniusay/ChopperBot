package org.example.init;

import org.example.constpool.ConstPool;
import org.example.constpool.ModuleName;
import org.example.constpool.PluginName;
import org.example.core.guard.HotModuleGuard;
import org.example.core.guard.HotModuleGuardInstance;
import org.example.plugin.CommonPlugin;
import org.example.plugin.annotation.Plugin;

import java.util.List;

/**
 * @author Genius
 * @date 2023/07/21 09:58
 **/

@Plugin(moduleName = ModuleName.HOT,
        pluginName = PluginName.HOT_GUARD_PLUGIN,
        needPlugin = {PluginName.FILE_CACHE_PLUGIN,PluginName.HOT_CONFIG_PLUGIN},
        pluginClass= HotModuleGuard.class )
public class HotGuardInitMachine extends CommonInitMachine{


    public HotGuardInitMachine(List<String> needPlugins, boolean isAutoStart, String moduleName, String name, Class<? extends CommonPlugin> clazz) {
        super(needPlugins, isAutoStart, moduleName, name, clazz);
    }

    /**
     * 初始化整个热度监控的环境
     * 1，获取热度监控的所有配置，并进行读取和初始化
     * 2，获得所有守卫并进行初始化
     * 3，初始化热度监控插件
     * @throws Exception
     */



    @Override
    public void shutdown() {
        super.shutdown();
        HotModuleGuardInstance.getInstance().close();
    }
}
