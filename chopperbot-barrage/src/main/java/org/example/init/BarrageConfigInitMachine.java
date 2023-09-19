package org.example.init;

import org.example.config.BarrageModuleConfig;
import org.example.constpool.ModuleName;
import org.example.constpool.PluginName;
import org.example.plugin.CommonPlugin;
import org.example.plugin.annotation.Plugin;

import java.util.List;

/**
 * @author Genius
 * @date 2023/09/14 00:50
 **/

@Plugin(moduleName = ModuleName.BARRAGE,
        pluginName = PluginName.BARRAGE_CONFIG,
        pluginName_CN = "弹幕模块配置文件",
        needPlugin = {PluginName.FILE_CACHE_PLUGIN},
        pluginClass= BarrageModuleConfig.class )
public class BarrageConfigInitMachine extends ConfigInitMachine{
    public BarrageConfigInitMachine(List<String> needPlugins, boolean isAutoStart, String moduleName, String name, Class<? extends CommonPlugin> clazz) {
        super(needPlugins, isAutoStart, moduleName, name, clazz);
    }
}
