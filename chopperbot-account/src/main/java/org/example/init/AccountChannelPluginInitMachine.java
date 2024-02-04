package org.example.init;

import org.example.constpool.ModuleName;
import org.example.constpool.PluginName;
import org.example.core.channel.AccountBindChannel;
import org.example.core.guard.VideoPushGuard;
import org.example.plugin.annotation.Plugin;
import org.example.pojo.AccountChannel;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author welsir
 * @Date 2023/11/20 16:09
 */
@Plugin(moduleName = ModuleName.ACCOUNT,
        pluginName = PluginName.CHANNEL,
        pluginName_CN = "账号管道插件",
        pluginDescription = "用于对管道的CRUD以及账号绑定",
        pluginClass= AccountBindChannel.class,
        springBootPlugin = true,
        ignore = true
)
@Component
public class AccountChannelPluginInitMachine extends SpringPlugInitMachine{
}
