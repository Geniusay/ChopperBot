package org.example.init;

import org.example.constpool.ModuleName;
import org.example.constpool.PluginName;
import org.example.core.auto.video.description.DescGeneratorPlugin;
import org.example.core.auto.video.title.TitleGeneratePlugin;
import org.example.plugin.annotation.Plugin;
import org.springframework.stereotype.Component;

/**
 * @author Genius
 * @date 2023/10/25 21:26
 **/

@Plugin(moduleName = ModuleName.SECTION_WORK,
        pluginName = PluginName.DESC_GENERATE,
        pluginName_CN = "标题生成插件",
        pluginDescription = "根据切片内容自动生成切片标题",
        pluginClass= DescGeneratorPlugin.class,
        springBootPlugin = true,
        ignore=true
)
@Component
public class DescGeneratorPluginInitMachine extends SpringPlugInitMachine{
}
