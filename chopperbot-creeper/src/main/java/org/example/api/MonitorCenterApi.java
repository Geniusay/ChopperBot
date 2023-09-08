package org.example.api;

import org.example.bean.MonitorVO;
import org.example.constpool.PluginName;
import org.example.core.taskmonitor.MonitorCenter;
import org.example.init.InitPluginRegister;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

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

    public MonitorVO hasMonitor(String taskId){
        MonitorCenter plugin = InitPluginRegister.getPlugin(PluginName.TASK_MONITOR_PLUGIN, MonitorCenter.class);
        assert plugin != null;
        String type;
        if ((type=plugin.getMonitorType(taskId))!=null) {
            return new MonitorVO(taskId,type);
        }
        return null;
    }

    public boolean stop(String taskId){
        MonitorCenter plugin = InitPluginRegister.getPlugin(PluginName.TASK_MONITOR_PLUGIN, MonitorCenter.class);
        assert plugin != null;
        return plugin.stop(taskId);
    }

    public List<MonitorVO> getStartMonitor(){
        MonitorCenter plugin = InitPluginRegister.getPlugin(PluginName.TASK_MONITOR_PLUGIN, MonitorCenter.class);
        assert plugin != null;
        Map<String, Future> monitorThreadMap = plugin.getMonitorThreadMap();
        List<MonitorVO> res = new ArrayList<>();
        monitorThreadMap.forEach((k,v)->{
            res.add(new MonitorVO(k,plugin.getMonitorType(k)));
        });
        return res;
    }

}
