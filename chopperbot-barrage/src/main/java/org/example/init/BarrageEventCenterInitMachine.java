package org.example.init;

import org.example.config.BarrageModuleConfig;
import org.example.constpool.ModuleName;
import org.example.constpool.PluginName;
import org.example.core.bgevnet.BarrageEvent;
import org.example.core.bgevnet.BarrageEventCenter;
import org.example.plugin.annotation.Plugin;
import org.springframework.stereotype.Component;

/**
 * @author Genius
 * @date 2023/09/14 01:13
 **/
@Plugin(moduleName = ModuleName.BARRAGE,
        pluginName = PluginName.BARRAGE_EVENT_PLUGIN,
        pluginName_CN = "弹幕事件响应中心（接收弹幕响应并进行评分，热点切割，情绪标签等操作）",
        needPlugin = {PluginName.BARRAGE_CONFIG},
        pluginClass= BarrageEventCenter.class,
        springBootPlugin = true
)
@Component
public class BarrageEventCenterInitMachine extends SpringPlugInitMachine{
}
