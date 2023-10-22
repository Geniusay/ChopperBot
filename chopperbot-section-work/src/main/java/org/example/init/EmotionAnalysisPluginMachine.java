package org.example.init;

import org.example.constpool.ModuleName;
import org.example.constpool.PluginName;
import org.example.core.analysis.EmotionAnalysisPlugin;
import org.example.plugin.annotation.Plugin;
import org.springframework.stereotype.Component;

/**
 * @Date 2023/10/16
 * @Author xiaochun
 */
@Plugin(moduleName = ModuleName.SECTION_WORK,
        pluginName = PluginName.EMOTION_ANALYSIS,
        pluginName_CN = "情感分析插件",
        pluginDescription = "根据弹幕内容，分析其情感倾向，并给出对应的情感标签",
        needPlugin = {PluginName.CHAT_GPT,PluginName.LABEL_MANAGER},
        pluginClass= EmotionAnalysisPlugin.class,
        springBootPlugin = true,
        ignore=true
)
@Component
public class EmotionAnalysisPluginMachine extends SpringPlugInitMachine{
}
