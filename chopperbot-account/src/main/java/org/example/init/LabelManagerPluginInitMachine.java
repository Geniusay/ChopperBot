package org.example.init;

import org.example.constpool.ModuleName;
import org.example.constpool.PluginName;
import org.example.core.gpt.ChatGPTPlugin;
import org.example.core.label.LabelManagerPlugin;
import org.example.plugin.SpringBootPlugin;
import org.example.plugin.annotation.Plugin;
import org.springframework.stereotype.Component;

/**
 * @author Genius
 * @date 2023/10/23 00:13
 **/

@Plugin(moduleName = ModuleName.ACCOUNT,
        pluginName = PluginName.LABEL_MANAGER,
        pluginName_CN = "视频标签管理插件",
        needPlugin = {},
        pluginClass= LabelManagerPlugin.class,
        springBootPlugin = true
)
@Component
public class LabelManagerPluginInitMachine extends SpringPlugInitMachine {
}
