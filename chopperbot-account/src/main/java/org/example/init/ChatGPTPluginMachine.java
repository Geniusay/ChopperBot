package org.example.init;

import org.example.constpool.ModuleName;
import org.example.constpool.PluginName;
import org.example.core.gpt.ChatGPTPlugin;
import org.example.init.SpringPlugInitMachine;
import org.example.plugin.annotation.Plugin;
import org.springframework.stereotype.Component;

/**
 * @Date 2023/10/11
 * @Author xiaochun
 */
@Plugin(moduleName = ModuleName.ACCOUNT,
        pluginName = PluginName.CHAT_GPT,
        pluginName_CN = "ChatGPT插件",
        needPlugin = {},
        pluginClass= ChatGPTPlugin.class,
        springBootPlugin = true
)
@Component
public class ChatGPTPluginMachine extends SpringPlugInitMachine {

}
