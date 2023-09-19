package org.example.core.taskcenter.observer;

import org.example.constpool.PluginName;
import org.example.core.taskcenter.task.ReptileTask;
import org.example.core.taskmonitor.MonitorCenter;
import org.example.plugin.PluginCheckAndDo;
import org.springframework.stereotype.Component;

/**
 * @author Genius
 * @date 2023/09/14 22:01
 **/
@Component
public class MonitorTaskObserver extends AbstractTaskCenterObserver {
    @Override
    public void onAlready(ReptileTask task) {

    }

    @Override
    public void onRunning(ReptileTask task) {
        PluginCheckAndDo.CheckAndDo((plugin)->{
            ((MonitorCenter)plugin).register(task.getTaskId(), task.getLoadTask());
        }, PluginName.TASK_MONITOR_PLUGIN);
    }

    @Override
    public void onFinish(ReptileTask task) {
        PluginCheckAndDo.CheckAndDo((plugin)->{
            ((MonitorCenter)plugin).close(task.getTaskId());
        },PluginName.TASK_MONITOR_PLUGIN);
    }

    @Override
    public void send() {

    }

    @Override
    public boolean isMe(String taskId) {
        return true;
    }
}
