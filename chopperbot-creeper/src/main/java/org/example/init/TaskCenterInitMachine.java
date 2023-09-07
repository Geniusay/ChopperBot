package org.example.init;

import org.example.constpool.ModuleName;
import org.example.constpool.PluginName;
import org.example.plugin.annotation.Plugin;
import org.example.core.taskcenter.TaskCenter;

import java.util.List;

/**
 * @author Genius
 * @date 2023/07/28 23:49
 **/

@Plugin(moduleName = ModuleName.CREEPER,
        pluginName = PluginName.TASK_CENTER_PLUGIN,
        pluginName_CN = "爬虫任务中心插件",
        pluginDescription = "负责执行管理监控当前所有的爬虫任务",
        needPlugin = {PluginName.FILE_CACHE_PLUGIN,PluginName.CREEPER_CONFIG_PLUGIN,PluginName.CREEPER_MANAGER_PLUGIN},
        pluginClass= TaskCenter.class )
public class TaskCenterInitMachine extends CommonInitMachine{


    public TaskCenterInitMachine(List<String> needPlugins, boolean isAutoStart, String moduleName, String name, Class clazz) {
        super(needPlugins, isAutoStart, moduleName, name, clazz);
    }

}
