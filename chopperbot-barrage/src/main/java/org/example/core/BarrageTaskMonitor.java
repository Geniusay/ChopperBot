package org.example.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.core.taskmonitor.CommonTaskMonitor;
import org.example.ws.MessageHandlerFactory;

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
                            "pre",preDownloadBarrage,
                            "total",totalBarrage,
                            "time",timeConsuming()
                    ).toString()
            );
        }
        //TODO 有线程安全问题，但是影响不大
        preDownloadBarrage = 0;
    }

}
