package org.example.controller;


import com.genius.assistant.common.Result;
import org.example.api.TaskCenterApi;
import org.example.bean.ReptileTaskVO;
import org.example.constpool.PluginName;
import org.example.plugin.annotation.CheckPlugin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/creeper/taskCenter")
public class TaskCenterController {

    @Resource
    TaskCenterApi taskCenterApi;

    @CheckPlugin(needPlugin = {PluginName.TASK_CENTER_PLUGIN})
    @GetMapping("/getAllTask")
    public Result getAllTaskCenter(){
        List<ReptileTaskVO> allTask = taskCenterApi.getAllTask();
        return Result.success(Map.of(
                "list",allTask
        ));
    }

    @CheckPlugin(needPlugin = {PluginName.TASK_CENTER_PLUGIN})
    @GetMapping("/stop")
    public Result stopTask(@RequestParam String taskId){
        boolean isStop = taskCenterApi.stopRunningTask(taskId);
        return Result.success(
                Map.of(
                        "taskId",taskId,
                        "stop", isStop
                )
        );
    }
}
