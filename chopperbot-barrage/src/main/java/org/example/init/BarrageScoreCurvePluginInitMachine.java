package org.example.init;

import org.example.constpool.ModuleName;
import org.example.constpool.PluginName;
import org.example.core.bgevnet.bgscore.BarrageScoreCurvePlugin;
import org.example.plugin.annotation.Plugin;
import org.springframework.stereotype.Component;

/**
 * @author Genius
 * @date 2023/09/14 01:11
 **/
@Plugin(moduleName = ModuleName.BARRAGE,
        pluginName = PluginName.BARRAGE_SCORE_CURVE_PLUGIN,
        pluginName_CN = "弹幕评分曲线插件",
        pluginDescription = "计算一场直播的整体弹幕得分曲线",
        needPlugin = {PluginName.BARRAGE_CONFIG},
        pluginClass= BarrageScoreCurvePlugin.class,
        springBootPlugin = true
)
@Component
public class BarrageScoreCurvePluginInitMachine extends SpringPlugInitMachine{
}
