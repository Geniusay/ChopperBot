package org.example.init;

import org.example.constpool.ModuleName;
import org.example.constpool.PluginName;
import org.example.core.bgevnet.instantslicing.InstantSlicingPlugin;
import org.example.plugin.annotation.Plugin;
import org.springframework.stereotype.Component;

/**
 * @author Genius
 * @date 2023/09/17 21:58
 **/

@Plugin(moduleName = ModuleName.BARRAGE,
        pluginName = PluginName.INSTANT_SLICING_PLUGIN,
        pluginName_CN = "即时切片插件，拥有多种算法进行实时快速切片",
        needPlugin = {PluginName.BARRAGE_CONFIG,PluginName.BARRAGE_EVENT_PLUGIN},
        pluginClass= InstantSlicingPlugin.class,
        springBootPlugin = true
)
@Component
public class InstantSlicingPluginInitMachine extends SpringPlugInitMachine{
}
