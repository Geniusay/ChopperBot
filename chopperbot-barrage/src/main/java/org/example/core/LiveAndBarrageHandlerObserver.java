package org.example.core;

import org.example.bean.Live;
import org.example.constpool.ConstGroup;
import org.example.constpool.PluginName;
import org.example.core.bgevnet.BarrageEvent;
import org.example.core.bgevnet.BarrageEventCenter;
import org.example.core.bgevnet.instantslicing.InstantSlicingPlugin;
import org.example.core.taskcenter.observer.AbstractTaskCenterObserver;
import org.example.core.taskcenter.task.ReptileTask;
import org.example.init.InitPluginRegister;
import org.example.plugin.PluginCheckAndDo;
import org.springframework.stereotype.Component;

/**
 * @author Genius
 * @date 2023/09/17 21:49
 **/
@Component
public class LiveAndBarrageHandlerObserver extends AbstractTaskCenterObserver {
    @Override
    public void onAlready(ReptileTask task) {

    }

    @Override
    public void onRunning(ReptileTask task) {
        PluginCheckAndDo.CheckAndDo((plugin)->{
            ((InstantSlicingPlugin)plugin).addTask(task);
        }, PluginName.INSTANT_SLICING_PLUGIN);
    }

    @Override
    public void onFinish(ReptileTask task) {
        if(!InitPluginRegister.isRegister(PluginName.INSTANT_SLICING_PLUGIN)){
            PluginCheckAndDo.CheckAndDo((plugin)->{
                Object param = task.getRequest().getParam();

                if(param instanceof Live){
                    BarrageEvent event = new BarrageEvent(
                            ((Live) param).getPlatform(),
                            "online",
                            ((Live) param).getLiver(),
                            task.getLoadConfig().getStartTime()
                    );
                    ((BarrageEventCenter)plugin).event(event);
                }
            },PluginName.BARRAGE_EVENT_PLUGIN);
        }
    }

    @Override
    public void send() {

    }

    @Override
    public boolean isMe(String taskId) {
        return taskId.startsWith(ConstGroup.LIVE_ONLINE);
    }
}
