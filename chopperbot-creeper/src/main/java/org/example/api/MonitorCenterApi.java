package org.example.api;

import org.example.constpool.PluginName;
import org.example.core.taskmonitor.MonitorCenter;
import org.example.init.InitPluginRegister;
import org.springframework.stereotype.Component;

/**
 * @author Genius
 * @date 2023/09/07 01:26
 **/
@Component
public class MonitorCenterApi {

    public boolean start(String taskId){
        MonitorCenter plugin = InitPluginRegister.getPlugin(PluginName.TASK_MONITOR_PLUGIN, MonitorCenter.class);
        assert plugin != null;
        return plugin.start(taskId);
    }

    public boolean stop(String taskId){
        MonitorCenter plugin = InitPluginRegister.getPlugin(PluginName.TASK_MONITOR_PLUGIN, MonitorCenter.class);
        assert plugin != null;
        return plugin.stop(taskId);
    }
}
