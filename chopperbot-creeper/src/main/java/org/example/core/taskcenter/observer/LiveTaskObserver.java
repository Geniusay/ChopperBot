package org.example.core.taskcenter.observer;

import org.example.constpool.ConstGroup;
import org.example.constpool.PluginName;
import org.example.core.manager.CreeperGroupCenter;
import org.example.core.taskcenter.TaskCenter;
import org.example.core.taskcenter.request.ReptileRequest;
import org.example.core.taskcenter.task.ReptileTask;
import org.example.init.InitPluginRegister;
import org.example.plugin.annotation.Plugin;
import org.springframework.stereotype.Component;

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
        ReptileRequest request = task.getRequest();
        String groupName = request.getCreeperGroup().replace(ConstGroup.LIVE_ONLINE,ConstGroup.BARRAGE_ONLINE);
        ReptileRequest barrageReq = new ReptileRequest(
                (t)->{

                },groupName,request.getParam()
        );
        TaskCenter plugin = InitPluginRegister.getPlugin(PluginName.TASK_CENTER_PLUGIN, TaskCenter.class);
        assert plugin != null;
        ReptileTask reptileTask = plugin.getReptileTask(barrageReq);
        if(reptileTask!=null){
            reptileTask.getLoadConfig().setStartTime(task.getLoadConfig().getStartTime());
        }
        plugin.request(reptileTask);
    }

    @Override
    public void onRunning(ReptileTask task) {

    }

    @Override
    public void onFinish(ReptileTask task) {
        String lid = task.getTaskId();
        String bid = lid.replace("live_","barrage_");
        taskCenter.stopTask(bid);
    }

    @Override
    public boolean isMe(String taskId) {
        return taskId.startsWith(ConstGroup.LIVE_ONLINE);
    }
}
