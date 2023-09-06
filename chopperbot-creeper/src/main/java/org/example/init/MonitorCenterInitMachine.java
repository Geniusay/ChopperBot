package org.example.init;

import org.example.constpool.ModuleName;
import org.example.constpool.PluginName;
import org.example.core.taskcenter.TaskCenter;
import org.example.core.taskmonitor.MonitorCenter;
import org.example.plugin.CommonPlugin;
import org.example.plugin.annotation.Plugin;

import java.util.List;

/**
 * @author Genius
 * @date 2023/09/07 01:24
 **/
@Plugin(moduleName = ModuleName.CREEPER,
        pluginName = PluginName.TASK_MONITOR_PLUGIN,
        pluginName_CN = "任务下载监控中心",
        pluginDescription = "负责获取当前任务实时下载情况",
        needPlugin = {PluginName.TASK_CENTER_PLUGIN},
        pluginClass= MonitorCenter.class )
public class MonitorCenterInitMachine extends CommonInitMachine{

    public MonitorCenterInitMachine(List<String> needPlugins, boolean isAutoStart, String moduleName, String name, Class<? extends CommonPlugin> clazz) {
        super(needPlugins, isAutoStart, moduleName, name, clazz);
    }
}
