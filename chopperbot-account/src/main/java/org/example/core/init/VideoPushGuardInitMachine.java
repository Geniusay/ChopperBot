package org.example.core.init;

import org.example.constpool.ModuleName;
import org.example.constpool.PluginName;
import org.example.core.guard.VideoPushGuard;
import org.example.init.CommonInitMachine;
import org.example.plugin.CommonPlugin;
import org.example.plugin.annotation.Plugin;

import java.util.List;

/**
 * @Description
 * @Author welsir
 * @Date 2023/10/13 19:09
 */
@Plugin(moduleName = ModuleName.ACCOUNT,
        pluginName = PluginName.VIDEO_PUSH_PLUGIN,
        pluginName_CN = "视频推送插件",
        pluginDescription = "用于将切片好的视频自动推送至对应类型账号",
        needPlugin = {},
        pluginClass= VideoPushGuard.class )
public class VideoPushGuardInitMachine extends CommonInitMachine {
    public VideoPushGuardInitMachine(List<String> needPlugins, boolean isAutoStart, String moduleName, String name, Class<? extends CommonPlugin> clazz) {
        super(needPlugins, isAutoStart, moduleName, name, clazz);
    }
}
