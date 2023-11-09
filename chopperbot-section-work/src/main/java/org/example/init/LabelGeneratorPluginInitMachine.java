package org.example.init;

import org.example.constpool.ModuleName;
import org.example.constpool.PluginName;
import org.example.core.auto.video.label.LabelGeneratePlugin;
import org.example.plugin.annotation.Plugin;
import org.springframework.stereotype.Component;

@Plugin(moduleName = ModuleName.SECTION_WORK,
        pluginName = PluginName.LABEL_GENERATE,
        pluginName_CN = "视频情感标签生成插件",
        pluginDescription = "根据切片内容自动生成视频情感标签",
        pluginClass= LabelGeneratePlugin.class,
        springBootPlugin = true,
        ignore=true
)
@Component
public class LabelGeneratorPluginInitMachine  extends SpringPlugInitMachine{
}
