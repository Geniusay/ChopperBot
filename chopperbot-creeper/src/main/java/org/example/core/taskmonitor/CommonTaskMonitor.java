package org.example.core.taskmonitor;

import lombok.Data;
import org.example.util.ThreadUtil;
import org.example.util.TimeUtil;
import org.example.ws.MessageHandlerFactory;
import org.example.ws.handler.AbstractMessageHandler;
import org.example.ws.handler.MessageProtocol;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author Genius
 * @date 2023/09/06 17:51
 **/
@Data
public abstract class CommonTaskMonitor implements TaskMonitor {

    private boolean isOpen = true;

    private String taskId;

    protected String monitorType;

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
        isOpen = false;
        return true;
    }

    @Override
    public boolean close() {
        return this.getHandler().close(taskId);
    }

    public String timeConsuming(){
        long time = (System.currentTimeMillis() - startTime)/ 1000;
        return TimeUtil.getHMS(time);
    }

    public String getMonitorType(){
        return monitorType;
    }

}
