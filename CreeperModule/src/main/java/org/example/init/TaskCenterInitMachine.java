package org.example.init;

import org.example.constpool.PluginName;
import org.example.log.ChopperLogFactory;
import org.example.log.LoggerType;
import org.example.taskcenter.TaskCenter;
import org.example.thread.oddjob.OddJobBoy;
import org.slf4j.Logger;

import java.util.List;
import java.util.Objects;

/**
 * @author Genius
 * @date 2023/07/28 23:49
 **/
public class TaskCenterInitMachine extends CommonInitMachine{

    public TaskCenterInitMachine() {
        super(List.of(PluginName.FILE_CACHE_PLUGIN),
                ChopperLogFactory.getLogger(LoggerType.Creeper),
                PluginName.TASK_CENTER_PLUGIN);
    }

    @Override
    public boolean init() {
        if(checkNeedPlugin()){
            try {
                TaskCenter center = TaskCenter.center();
                if(center==null){
                    return fail();
                }
                center.guardian();
            }catch (Exception e){
                return fail();
            }
            registerPlugin();
            return success();
        }
        return false;
    }

    @Override
    public void shutdown() {
        TaskCenter center = TaskCenter.center();
        if(center!=null){
            center.shutdown();
        }
    }

    @Override
    public void afterInit() {
        //爬虫任务恢复
        TaskCenter.center().restoreTaskCenter();
    }
}
