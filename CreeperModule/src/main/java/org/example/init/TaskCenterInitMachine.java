package org.example.init;

import org.example.taskcenter.TaskCenter;
import org.example.thread.oddjob.OddJobBoy;

import java.util.Objects;

/**
 * @author Genius
 * @date 2023/07/28 23:49
 **/
public class TaskCenterInitMachine extends CommonInitMachine{

    @Override
    public boolean init() {
        try {
            TaskCenter center = TaskCenter.center();
            if(center==null){
                return false;
            }
            OddJobBoy.Boy().addWork(
                    center::work
            );
        }catch (InterruptedException e){
            return false;
        }
        return true;
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
        Objects.requireNonNull(TaskCenter.center()).restoreTaskCenter();
    }
}
