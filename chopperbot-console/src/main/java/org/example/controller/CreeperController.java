package org.example.controller;

import com.genius.assistant.common.Result;
import org.example.api.HotModuleApi;
import org.example.bean.Live;
import org.example.bean.ReptileTaskVO;
import org.example.constpool.ConstPool;
import org.example.constpool.PluginName;
import org.example.core.HotModuleDataCenter;
import org.example.core.manager.CreeperBean;
import org.example.plugin.annotation.CheckPlugin;
import org.example.service.CreeperService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Genius
 * @date 2023/09/06 00:58
 **/
@RestController
@RequestMapping("/creeper")
public class CreeperController {

    @Resource
    CreeperService creeperService;

    @CheckPlugin(needPlugin = {PluginName.TASK_CENTER_PLUGIN})
    @GetMapping("/taskCenter/getAllTask")
    public Result getAllTaskCenter(){
        List<ReptileTaskVO> allTask = creeperService.taskCenterApi().getAllTask();
        return Result.success(Map.of(
                "list",allTask
        ));
    }

    @CheckPlugin(needPlugin = {PluginName.TASK_CENTER_PLUGIN})
    @GetMapping("/taskCenter/stop")
    public Result stopTask(@RequestParam String taskId){
        boolean isStop = creeperService.taskCenterApi().stopRunningTask(taskId);
        return Result.success(
                Map.of(
                        "taskId",taskId,
                        "stop", isStop
                )
        );
    }

    @CheckPlugin(needPlugin = {PluginName.CREEPER_MANAGER_PLUGIN})
    @GetMapping("/creeperManager/getAllCreeper")
    public Result getAllCreeper(){
        List<CreeperBean> allCreeper = creeperService.creeperManagerApi().getAllCreeper();
        return Result.success(Map.of(
                "list",allCreeper
        ));
    }

    @CheckPlugin(needPlugin = {PluginName.TASK_MONITOR_PLUGIN})
    @GetMapping("/monitor/start")
    public Result startMonitor(@RequestParam String taskId){
        boolean start = creeperService.monitorApi().start(taskId);
        return Result.success(
                Map.of("taskId",taskId,
                        "start",start)
        );
    }

    @CheckPlugin(needPlugin = {PluginName.TASK_MONITOR_PLUGIN})
    @GetMapping("/monitor/stop")
    public Result stopMonitor(@RequestParam String taskId){
        boolean isStop = creeperService.monitorApi().stop(taskId);
        return Result.success(
                Map.of(
                        "taskId",taskId,
                        "stop", isStop
                )
        );
    }
}
