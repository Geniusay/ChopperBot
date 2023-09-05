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
        pluginName_CN = "热门推荐插件",
        pluginDescription = "推荐各个平台的热门直播，根据跟风狗设置，自动爬取直播",
        needPlugin = {PluginName.HOT_CONFIG_PLUGIN,PluginName.HOT_GUARD_PLUGIN,PluginName.CREEPER_MANAGER_PLUGIN},
        pluginClass= HeatRecommendation.class )
public class HeatRecommendationInitMachine extends CommonInitMachine{

    public HeatRecommendationInitMachine(List<String> needPlugins, boolean isAutoStart, String moduleName, String name, Class<? extends CommonPlugin> clazz) {
        super(needPlugins, isAutoStart, moduleName, name, clazz);
    }

}
