package org.example.core.guard;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.bean.Live;
import org.example.bean.hotmodule.HotModuleList;
import org.example.constpool.PluginName;
import org.example.core.HotModuleDataCenter;
import org.example.core.control.HotModuleLoadTask;
import org.example.init.HeatRecommendationInitMachine;
import org.example.init.InitPluginRegister;
import org.example.log.ResultLogger;
import org.slf4j.Logger;

import java.util.List;

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

    private Logger logger;
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
            HotModuleDataCenter.DataCenter().addLiveList(platform,(List<Live>) data);
            //查看热度推送插件是否装载，如果装载则进行热度推送
            if(InitPluginRegister.isRegister(PluginName.HOT_RECOMMENDATION_PLUGIN)){
                HeatRecommendationInitMachine.heatRecommendation.sendHotEvent(platform);
            }
        }

    }

    @Override
    public void successLog() {
        logger.info("{} successfully finish!",guardName);
    }

    @Override
    public void successLog(String str) {

    }

    @Override
    public void failLog() {
        logger.error("{} finish error,cancel this task!",guardName);
    }

    @Override
    public void failLog(String str) {
        logger.error("{} fail try to redo,retry times:{}!",guardName,str);
    }

}
