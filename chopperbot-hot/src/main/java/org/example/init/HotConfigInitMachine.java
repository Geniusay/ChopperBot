package org.example.init;

/**
 * @author Genius
 * @date 2023/07/21 00:18
 **/

import org.example.config.HotModuleConfig;
import org.example.constpool.ConstPool;
import org.example.constpool.ModuleName;
import org.example.constpool.PluginName;
import org.example.plugin.CommonPlugin;
import org.example.plugin.annotation.Plugin;

import java.util.List;

/**
 * 热门模块配置文件初始化机器
 */
@Plugin(moduleName = ModuleName.HOT,
        pluginName = PluginName.HOT_CONFIG_PLUGIN,
        pluginName_CN = "热门模块配置文件",
        needPlugin = {PluginName.FILE_CACHE_PLUGIN},
        pluginClass= HotModuleConfig.class )
public class HotConfigInitMachine extends ConfigInitMachine {


    public HotConfigInitMachine(List<String> needPlugins, boolean isAutoStart, String moduleName, String name, Class<? extends CommonPlugin> clazz) {
        super(needPlugins, isAutoStart, moduleName, name, clazz);
    }

}
