package org.example.core.taskmonitor;

import lombok.Data;
import org.example.util.ThreadUtil;
import org.example.util.TimeUtil;
import org.example.ws.MessageHandlerFactory;
import org.example.ws.handler.AbstractMessageHandler;
import org.example.ws.handler.MessageProtocol;

/**
 * @author Genius
 * @date 2023/09/06 17:51
 **/
@Data
public abstract class CommonTaskMonitor implements TaskMonitor {

    private boolean isOpen = true;

    private String taskId;

    private long startTime = System.currentTimeMillis();

    protected AbstractMessageHandler handler;


    public CommonTaskMonitor() {
        handler = MessageHandlerFactory.getHandler(MessageProtocol.getHead("monitor"));
    }

    public abstract void run();
    @Override
    public void monitor() {
        if(isOpen){
            run();
        }
    }

    @Override
    public boolean isOpen() {
        return isOpen;
    }

    @Override
    public boolean stop() {
        return false;
    }

    public String timeConsuming(){
        long time = (System.currentTimeMillis() - startTime)/ 1000;
        return TimeUtil.getHMS(time);
    }
}
