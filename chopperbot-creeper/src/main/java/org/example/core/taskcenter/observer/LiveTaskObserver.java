package org.example.core.taskcenter.observer;

import org.example.constpool.ConstGroup;
import org.example.constpool.PluginName;
import org.example.core.manager.CreeperGroupCenter;
import org.example.core.taskcenter.TaskCenter;
import org.example.core.taskcenter.request.ReptileRequest;
import org.example.core.taskcenter.task.ReptileTask;
import org.example.core.taskcenter.task.TaskStatus;
import org.example.init.InitPluginRegister;
import org.example.plugin.annotation.Plugin;
import org.springframework.stereotype.Component;

import static org.example.constpool.ConstPool.NULL_TIME;

/**
 * @author Genius
 * @date 2023/09/14 21:43
 **/
@Component
public class LiveTaskObserver extends AbstractTaskCenterObserver {


    @Override
    public void send() {

    }

    @Override
    public void onAlready(ReptileTask task) {

    }

    @Override
    public void onRunning(ReptileTask task) {
        ReptileRequest request = task.getRequest();
        //重置开始时间，严格保证
        while(true){
            if(!task.getStartTime().equals(NULL_TIME)){
                task.getLoadConfig().setStartTime(task.getStartTime());
                break;
            }
            if(task.getType().equals(TaskStatus.Finish))break;
        }
        String groupName = request.getCreeperGroup().replace(ConstGroup.LIVE_ONLINE,ConstGroup.BARRAGE_ONLINE);
        ReptileRequest barrageReq = new ReptileRequest(
                (t)->{

                },groupName,request.getParam()
        );
        TaskCenter plugin = InitPluginRegister.getPlugin(PluginName.TASK_CENTER_PLUGIN, TaskCenter.class);
        assert plugin != null;
        ReptileTask reptileTask = plugin.getReptileTask(barrageReq);
        if(reptileTask!=null&&task.getType().equals(TaskStatus.Running)){
            reptileTask.getLoadConfig().setStartTime(task.getLoadConfig().getStartTime());
        }
        plugin.request(reptileTask);
    }

    @Override
    public void onFinish(ReptileTask task) {
        String lid = task.getTaskId();
        String bid = lid.replace(ConstGroup.LIVE_ONLINE,ConstGroup.BARRAGE_ONLINE);
        taskCenter.stopTask(bid);
    }

    @Override
    public boolean isMe(String taskId) {
        return taskId.startsWith(ConstGroup.LIVE_ONLINE);
    }
}
