package org.example.init;

import org.example.constpool.ModuleName;
import org.example.constpool.PluginName;
import org.example.core.focus.LiverFollower;
import org.example.plugin.annotation.Plugin;
import org.springframework.stereotype.Component;

/**
 * @author Genius
 * @date 2023/09/10 00:29
 **/

@Plugin(moduleName = ModuleName.HOT,
        pluginName = PluginName.HOT_LIVER_FOLLOWER,
        pluginName_CN = "主播关注插件",
        pluginDescription = "时时刻刻监视主播--的直播录播情况",
        needPlugin = {PluginName.FILE_CACHE_PLUGIN,PluginName.HOT_CONFIG_PLUGIN,PluginName.TASK_CENTER_PLUGIN},
        pluginClass= LiverFollower.class,
        springBootPlugin = true
)
@Component
public class LiverFollowerInitMachine extends SpringPlugInitMachine{
}
