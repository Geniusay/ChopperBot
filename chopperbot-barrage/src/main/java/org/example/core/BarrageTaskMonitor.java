package org.example.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.core.taskmonitor.CommonTaskMonitor;
import org.example.ws.MessageHandlerFactory;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Genius
 * @date 2023/09/07 01:29
 **/
public class BarrageTaskMonitor extends CommonTaskMonitor {

    private int totalBarrage = 0;

    private int preDownloadBarrage = 0;

    public BarrageTaskMonitor() {
        monitorType = "barrage";
    }

    public void addBarrage(){
        addBarrage(1);
    }

    public void addBarrage(int num){
        preDownloadBarrage = num;
        totalBarrage += num;
    }
    @Override
    public void run() {
        if(handler!=null){
            handler.wrapperAndSend(
                    Map.of(
                            "taskId",getTaskId(),
                            "monitor",monitorType,
                            "pre",preDownloadBarrage,
                            "total",totalBarrage,
                            "type","barrage",
                            "useTime",timeConsuming(),
                            "time", LocalDateTime.now()
                    ),getTaskId()
            );
        }
    }

}
