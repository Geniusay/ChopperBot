package org.example.init;

import org.example.constpool.ModuleName;
import org.example.constpool.PluginName;
import org.example.core.section.VideoSectionWorkShop;
import org.example.plugin.annotation.Plugin;
import org.springframework.stereotype.Component;

/**
 * @author Genius
 * @date 2023/09/19 19:56
 **/
@Plugin(moduleName = ModuleName.SECTION,
        pluginName = PluginName.VIDEO_SECTION_WORK_SHOP,
        needPlugin = {PluginName.FILE_CACHE_PLUGIN},
        pluginName_CN = "切片车间",
        pluginClass= VideoSectionWorkShop.class,
        springBootPlugin = true
)
@Component
public class VideoSectionWorkShopInitMachine extends SpringPlugInitMachine{
}
