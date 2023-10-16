package org.example.core.taskmonitor;

import lombok.Data;
import org.example.constpool.PluginName;
import org.example.core.loadtask.LoadTask;
import org.example.plugin.CommonPlugin;
import org.example.thread.NamedThreadFactory;
import org.example.util.ExceptionUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author Genius
 * @date 2023/09/07 00:09
 **/
@Data
public class MonitorCenter extends CommonPlugin {

   // private Map<String,Class<? extends CommonTaskMonitor>> monitorClazzMap;
    private Map<String,CommonTaskMonitor> monitorMap;

    private ScheduledExecutorService monitorPool;

    private Map<String, Future> monitorThreadMap;

    public MonitorCenter(String module, String pluginName, List<String> needPlugins, boolean isAutoStart) {
        super(module, pluginName, needPlugins, isAutoStart);
    }

    @Override
    public boolean init() {
        monitorMap = new HashMap<>();
        //monitorClazzMap = new HashMap<>();
        monitorPool = Executors.newScheduledThreadPool(20,
                new NamedThreadFactory(PluginName.TASK_MONITOR_PLUGIN));

        monitorThreadMap = new HashMap<>();
        return super.init();
    }

    public void register(String taskId, LoadTask loadTask){
        try {
            if (loadTask.getClass().isAnnotationPresent(Monitor.class)) {
                Monitor annotation = loadTask.getClass().getAnnotation(Monitor.class);
                // monitorClazzMap.put(taskId,annotation.clazz());
                CommonTaskMonitor monitor = annotation.clazz().getDeclaredConstructor().newInstance();
                monitor.setTaskId(taskId);
                monitorMap.put(taskId,monitor);
            }else{
                this.info(String.format("%s no setting monitor", taskId));
            }
        }catch (Exception e){
            this.error(String.format("Error:%s", ExceptionUtil.getCause(e)));
        }
    }

    public boolean start(String taskId){
        CommonTaskMonitor monitor = getInitMonitor(taskId, CommonTaskMonitor.class);
        if(monitor!=null){
            if(monitorThreadMap.containsKey(taskId)){
                return true;
            }
            ScheduledFuture<?> scheduledFuture = monitorPool.scheduleWithFixedDelay(
                    monitor::monitor, 0, 1000, TimeUnit.MILLISECONDS
            );
            monitorThreadMap.put(taskId,scheduledFuture);
            this.info(String.format("%s start monitor success!", taskId));
            return true;
        }
        return false;
    }

    public boolean stop(String taskId){
        if(monitorMap.containsKey(taskId)){
            CommonTaskMonitor monitor = monitorMap.get(taskId);
            this.info(String.format("%s stop monitor success!", taskId));
            return monitor.stop();
        }
        return false;
    }

    public boolean close(String taskId){
        CommonTaskMonitor monitor;
         if((monitor=monitorMap.remove(taskId))!=null){
             monitor.close();
             Future future;
             if((future=monitorThreadMap.remove(taskId))!=null) {
                 future.cancel(true);
             }
             this.info(String.format("%s close monitor success!", taskId));
             return true;
         }
         return false;
    }

    public <T extends CommonTaskMonitor> T getInitMonitor(String taskId,Class<? extends CommonTaskMonitor> tClass){
        if (monitorMap.containsKey(taskId)) {
            return (T) monitorMap.get(taskId);
        }
        return null;
    }

    public String getMonitorType(String taskId){
        CommonTaskMonitor monitor = monitorMap.get(taskId);
        return monitor==null?null:monitor.getMonitorType();
    }
}
