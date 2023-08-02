package org.example.init;

import org.example.constpool.ConstPool;
import org.example.constpool.ModuleName;
import org.example.constpool.PluginName;
import org.example.core.recommend.HeatRecommendation;
import org.example.plugin.CommonPlugin;
import org.example.plugin.annotation.Plugin;

import java.util.List;

/**
 * @author Genius
 * @date 2023/07/29 14:48
 **/

@Plugin(moduleName = ModuleName.HOT,
        pluginName = PluginName.HOT_RECOMMENDATION_PLUGIN,
        needPlugin = {PluginName.HOT_CONFIG_PLUGIN,PluginName.HOT_GUARD_PLUGIN},
        pluginClass= HeatRecommendation.class )
public class HeatRecommendationInitMachine extends CommonInitMachine{


    public HeatRecommendationInitMachine(List<String> needPlugins, boolean isAutoStart, String moduleName, String name, Class<? extends CommonPlugin> clazz) {
        super(needPlugins, isAutoStart, moduleName, name, clazz);
    }


}
