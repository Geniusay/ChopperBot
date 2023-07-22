package org.example.guard;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.bean.HotLive;
import org.example.bean.hotmodule.HotModuleList;
import org.example.constpool.ConstPool;
import org.example.core.HotModuleDataCenter;
import org.example.core.control.HotModuleLoadTask;
import org.example.core.control.LoadTask;
import org.example.guard.HotModuleGuardInstance;
import org.example.log.HotModuleLogger;
import org.example.log.ResultLogger;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;

/**
 * @author Genius
 * @date 2023/07/21 02:13
 **/

/**
 * 热度监控守卫，负责执行各个平台的热度监控工作
 */
@Data
@AllArgsConstructor
public class Guard implements Runnable, ResultLogger {

    private String guardName;
    private HotModuleLoadTask task;

    private long delayTime;

    private int failRetryTimes;
    @Override
    public void run() {

        int retryTimes = 0;
        Object data = task.start();
        if(task.isFinish() == HotModuleLoadTask.FinishFlag.FINISH){
            successLog();
        }else{
            while(task.isFinish()== HotModuleLoadTask.FinishFlag.FAIL){
                if(retryTimes<failRetryTimes){
                    data = task.start();
                    failLog(String.valueOf(retryTimes++));
                }else{
                    failLog();
                    HotModuleGuardInstance.getInstance().unActiveGuard(this.guardName);
                }
            }
        }
        updateDataCenter(task.getClass().getName(),data);
    }

    private void updateDataCenter(String clazz, Object data){
        String[] split = clazz.split("\\.");
        String clazzName = split[split.length-1].toLowerCase();
        String platform = clazzName.split("hot")[0];

        if(clazzName.contains("module")){
            HotModuleDataCenter.DataCenter().addModuleList(platform,(HotModuleList) data);
        }else if(clazzName.contains("live")){
            HotModuleDataCenter.DataCenter().addLiveList(platform,(List<HotLive>) data);
        }
    }

    @Override
    public void successLog() {
        HotModuleLogger.logger.info("{} successfully finish!",guardName);
    }

    @Override
    public void successLog(String str) {

    }

    @Override
    public void failLog() {
        HotModuleLogger.logger.error("{} finish error,cancel this task!",guardName);
    }

    @Override
    public void failLog(String str) {
        HotModuleLogger.logger.error("{} fail try to redo,retry times:{}!",guardName,str);
    }

}
