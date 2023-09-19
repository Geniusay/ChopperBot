package org.example.api;

import org.example.bean.ReptileTaskVO;
import org.example.constpool.PluginName;
import org.example.core.taskcenter.TaskCenter;
import org.example.core.taskcenter.TaskLog;
import org.example.core.taskcenter.task.TaskRecord;
import org.example.init.InitPluginRegister;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Genius
 * @date 2023/09/05 23:56
 **/
@Component
public class TaskCenterApi {

    public boolean stopRunningTask(String taskId){
        TaskCenter plugin = InitPluginRegister.getPlugin(PluginName.TASK_CENTER_PLUGIN, TaskCenter.class);
        assert plugin != null;
        return plugin.stopTask(taskId);
    }

    public List<ReptileTaskVO> getAllTask(){
        TaskCenter plugin = InitPluginRegister.getPlugin(PluginName.TASK_CENTER_PLUGIN, TaskCenter.class);
        assert plugin != null;
        List<ReptileTaskVO> list = new ArrayList<>();
        Map<String, TaskLog> recordMap = plugin.getTaskCenterLogger().getTaskLogMap();
        recordMap.forEach(
                (k,v)->{

                    list.add(new ReptileTaskVO(
                            v.getTaskId(),v.getStartTime(),v.getEndTime(),v.getType().getName(),0
                    ));
                }
        );
        return list;
    }
}
