package org.example.init;

import org.example.constpool.ModuleName;
import org.example.constpool.PluginName;
import org.example.core.manager.LiveDownloadManager;
import org.example.plugin.CommonPlugin;
import org.example.plugin.annotation.Plugin;

import java.util.List;

/**
 * @author Genius
 * @date 2023/08/31 15:47
 **/

@Plugin(moduleName = ModuleName.LIVE,
        pluginName = PluginName.LIVE_MANAGER_PLUGIN,
        pluginName_CN = "直播下载监控插件",
        pluginDescription = "实时下载监控直播的插件",
        pluginClass= LiveDownloadManager.class )
public class LiveDownloadManagerInitMachine extends CommonInitMachine{

    public LiveDownloadManagerInitMachine(List<String> needPlugins, boolean isAutoStart, String moduleName, String name, Class<? extends CommonPlugin> clazz) {
        super(needPlugins, isAutoStart, moduleName, name, clazz);
    }


}
