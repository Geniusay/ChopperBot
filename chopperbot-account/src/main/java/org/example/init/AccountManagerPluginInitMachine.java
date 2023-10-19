package org.example.init;

import org.example.constpool.ModuleName;
import org.example.constpool.PluginName;
import org.example.core.account.AccountManagerPlugin;
import org.example.core.gpt.ChatGPTPlugin;
import org.example.plugin.annotation.Plugin;
import org.springframework.stereotype.Component;

/**
 * @author Genius
 * @date 2023/10/20 02:49
 **/
@Plugin(moduleName = ModuleName.ACCOUNT,
        pluginName = PluginName.ACCOUNT_MANAGER,
        pluginName_CN = "用户账号管理插件",
        needPlugin = {},
        pluginClass= AccountManagerPlugin.class,
        springBootPlugin = true
)
@Component
public class AccountManagerPluginInitMachine extends SpringPlugInitMachine{
}
