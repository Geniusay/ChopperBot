package org.example.controller;

import com.genius.assistant.common.Result;
import org.example.api.MonitorCenterApi;
import org.example.bean.MonitorVO;
import org.example.constpool.PluginName;
import org.example.plugin.annotation.CheckPlugin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/creeper/monitor")
public class MonitorController {

    @Resource
    private MonitorCenterApi monitorCenterApi;

    @CheckPlugin(needPlugin = {PluginName.TASK_MONITOR_PLUGIN})
    @GetMapping("/all")
    public Result allMonitor(){
        return Result.success(
                Map.of(
                        "list",monitorCenterApi.getStartMonitor()
                )
        );
    }

    @CheckPlugin(needPlugin = {PluginName.TASK_MONITOR_PLUGIN})
    @GetMapping("/start")
    public Result startMonitor(@RequestParam String taskId){
        MonitorVO monitor = monitorCenterApi.hasMonitor(taskId);
        if(monitor==null){
            return Result.error("400", String.format("%s 不存在监视器", taskId));
        }else{
            return Result.success(
                    Map.of("monitor",monitor)
            );
        }
    }

    @CheckPlugin(needPlugin = {PluginName.TASK_MONITOR_PLUGIN})
    @GetMapping("/stop")
    public Result stopMonitor(@RequestParam String taskId){
        boolean isStop = monitorCenterApi.stop(taskId);
        return Result.success(
                Map.of(
                        "taskId",taskId,
                        "stop", isStop
                )
        );
    }

}
