package org.example.init;

import org.example.constpool.ModuleName;
import org.example.constpool.PluginName;
import org.example.core.gpt.OpenAPIPlugin;
import org.example.plugin.annotation.Plugin;
import org.springframework.stereotype.Component;

/**
 * @Date 2023/10/11
 * @Author xiaochun
 */
@Plugin(moduleName = ModuleName.ACCOUNT,
        pluginName = PluginName.CHAT_GPT,
        pluginName_CN = "OpenAPI管理使用插件务",
        pluginDescription = "供用户管理和调用OpenAI中的相关服务",
        needPlugin = {},
        pluginClass= OpenAPIPlugin.class,
        springBootPlugin = true
)
@Component
public class OpenAPIPluginMachine extends SpringPlugInitMachine {

}
