package org.example.init;

import org.example.constpool.ModuleName;
import org.example.constpool.PluginName;
import org.example.core.bgevnet.bghot.BarragePopularRangePlugin;
import org.example.core.bgevnet.bgscore.BarrageScoreCurvePlugin;
import org.example.plugin.annotation.Plugin;
import org.springframework.stereotype.Component;

/**
 * @author Genius
 * @date 2023/09/19 17:48
 **/
@Plugin(moduleName = ModuleName.BARRAGE,
        pluginName = PluginName.BARRAGE_POPULAR_RANGE_PLUGIN,
        pluginName_CN = "弹幕热门区间算法插件",
        pluginDescription = "对已爬取直播的弹幕得分曲线进行计算，得出弹幕得分最高的区间",
        needPlugin = {PluginName.BARRAGE_SCORE_CURVE_PLUGIN},
        pluginClass= BarragePopularRangePlugin.class,
        springBootPlugin = true
)
@Component
public class BarragePopularRangePluginInitMachine extends SpringPlugInitMachine{
}
