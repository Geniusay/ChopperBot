package org.example.init.plugin;

import org.example.constpool.ModuleName;
import org.example.constpool.PluginName;
import org.example.init.SpringPlugInitMachine;
import org.example.log.notice.NoticePlugin;
import org.example.plugin.annotation.Plugin;
import org.springframework.stereotype.Component;

/**
 * @author Genius
 * @date 2023/10/19 18:38
 **/

@Plugin(moduleName = ModuleName.CHOPPER_BOT,
        pluginName = PluginName.NOTICE_PLUGIN,
        pluginName_CN = "消息通知插件",
        pluginDescription = "负责与客户端进行相关事宜通知插件",
        pluginClass= NoticePlugin.class,
        ignore = true,
        springBootPlugin = true
)
@Component
public class NoticePluginInitMachine extends SpringPlugInitMachine {
}
