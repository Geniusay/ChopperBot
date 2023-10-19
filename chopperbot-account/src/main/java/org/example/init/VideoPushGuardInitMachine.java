package org.example.init;

import org.example.constpool.ModuleName;
import org.example.constpool.PluginName;
import org.example.core.guard.VideoPushGuard;
import org.example.plugin.annotation.Plugin;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author welsir
 * @Date 2023/10/13 19:09
 */
@Plugin(moduleName = ModuleName.ACCOUNT,
        pluginName = PluginName.VIDEO_PUSH_PLUGIN,
        pluginName_CN = "视频推送插件",
        pluginDescription = "用于将切片好的视频自动推送至对应类型账号",
        needPlugin = {PluginName.ACCOUNT_MANAGER},
        pluginClass= VideoPushGuard.class,
        springBootPlugin = true,
        ignore = true
)
@Component
public class VideoPushGuardInitMachine extends SpringPlugInitMachine {

}
