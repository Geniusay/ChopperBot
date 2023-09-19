package org.example.init;

import org.example.constpool.ModuleName;
import org.example.constpool.PluginName;
import org.example.core.listen.BarrageFileMonitor;
import org.example.plugin.CommonPlugin;
import org.example.plugin.annotation.Plugin;

import java.util.List;

/**
 * @Description
 * @Author welsir
 * @Date 2023/8/3 0:35
 */

@Plugin(moduleName = ModuleName.BARRAGE,
        pluginName = PluginName.BARRAGE_FILE_PLUGIN,
        needPlugin = {PluginName.CREEPER_CONFIG_PLUGIN,PluginName.TASK_CENTER_PLUGIN},
        pluginClass= BarrageFileMonitor.class,
        autoStart = false
)
public class BarrageFileListenInitMachine extends CommonInitMachine {
    public BarrageFileListenInitMachine(List<String> needPlugins, boolean isAutoStart, String moduleName, String name, Class<? extends CommonPlugin> clazz) {
        super(needPlugins, isAutoStart, moduleName, name, clazz);
    }
}
