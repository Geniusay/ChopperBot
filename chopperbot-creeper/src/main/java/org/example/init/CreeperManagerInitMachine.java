package org.example.init;

import org.example.config.CreeperConfigFile;
import org.example.constpool.ModuleName;
import org.example.constpool.PluginName;
import org.example.core.manager.CreeperManager;
import org.example.plugin.CommonPlugin;
import org.example.plugin.annotation.Plugin;

import java.util.List;

/**
 * @author Genius
 * @date 2023/08/18 04:40
 **/

@Plugin(moduleName = ModuleName.CREEPER,
        pluginName = PluginName.CREEPER_MANAGER_PLUGIN,
        pluginName_CN = "爬虫管理中心",
        pluginClass= CreeperManager.class )
public class CreeperManagerInitMachine extends CommonInitMachine{

    public CreeperManagerInitMachine(List<String> needPlugins, boolean isAutoStart, String moduleName, String name, Class<? extends CommonPlugin> clazz) {
        super(needPlugins, isAutoStart, moduleName, name, clazz);
    }

    @Override
    public boolean init() {
        if (super.init()) {
            return true;
        }
        return false;
    }

    @Override
    public void successLog() {
        successLog(String.format("[✔] [%s] find %s creeper,[%s] init success!",pluginName,
                ((CreeperManager) plugin).getCreeperBeans().size(),
                pluginName));
    }
}
