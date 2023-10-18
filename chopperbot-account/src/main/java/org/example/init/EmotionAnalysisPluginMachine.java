package org.example.init;

import org.example.constpool.ModuleName;
import org.example.constpool.PluginName;
import org.example.core.analysis.EmotionAnalysisPlugin;
import org.example.core.gpt.ChatGPTPlugin;
import org.example.plugin.annotation.Plugin;
import org.springframework.stereotype.Component;

/**
 * @Date 2023/10/16
 * @Author xiaochun
 */
@Plugin(moduleName = ModuleName.ACCOUNT,
        pluginName = PluginName.EMOTION_ANALYSIS,
        pluginName_CN = "情感分析插件",
        needPlugin = {PluginName.CHAT_GPT},
        pluginClass= EmotionAnalysisPlugin.class,
        springBootPlugin = true
)
@Component
public class EmotionAnalysisPluginMachine extends SpringPlugInitMachine{
}
