package org.example.init;

import org.example.constpool.ModuleName;
import org.example.constpool.PluginName;
import org.example.core.exchange.PostWorkerManager;
import org.example.core.guard.VideoPushChannelGuard;
import org.example.plugin.annotation.Plugin;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author welsir
 * @Date 2024/5/31 17:15
 */
@Plugin(moduleName = ModuleName.ACCOUNT,
        pluginName = PluginName.POST_WORKER,
        pluginName_CN = "快递员插件",
        pluginDescription = "用于将切片仓库中包装好的切片推送至用户管道",
        pluginClass= PostWorkerManager.class,
        springBootPlugin = true,
        ignore = true
)
@Component
public class PostWorkerPluginInitMachine extends SpringPlugInitMachine{
}
