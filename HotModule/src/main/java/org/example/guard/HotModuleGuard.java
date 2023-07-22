package org.example.guard;

import org.example.bean.HotLive;
import org.example.bean.hotmodule.HotModuleList;
import org.example.cache.FileCache;
import org.example.cache.FileCacheManager;
import org.example.cache.FileCacheManagerInstance;
import org.example.config.HotModuleConfig;
import org.example.constpool.HotModuleConstPool;
import org.example.core.control.HotModuleLoadTask;
import org.example.thread.NamedThreadFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author Genius
 * @date 2023/07/19 03:21
 **/
public class HotModuleGuard {

    private List<Guard> guards; //热度监控守卫列表，用于初始化一开始的热度监控列表
    private ScheduledExecutorService hotModuleGuardPool;    //热度监控守卫 定时线程池
    private Map<String,ScheduledFuture> runningGuards;      // 运行的热度监控守卫




    protected HotModuleGuard(List<Guard> guards,int guardNum){
        this.guards = guards;
        this.hotModuleGuardPool =  Executors.newScheduledThreadPool(guardNum, new NamedThreadFactory("HotModuleGuard"));
        runningGuards = new ConcurrentHashMap<>();
    }


    private void guardStart(Guard guard){
        ScheduledFuture<?> scheduledFuture = hotModuleGuardPool.scheduleWithFixedDelay(
                guard, 0, guard.getDelayTime(), TimeUnit.MILLISECONDS
        );
        runningGuards.put(guard.getGuardName(),scheduledFuture);
    }
    public void start(){
        if(runningGuards.size()==0){
            try {
                for (Guard guard : guards) {
                    guardStart(guard);
                }
            }catch (Exception e){
                throw e;
            }
        }
    }

    public boolean close(){
        hotModuleGuardPool.shutdown();
        runningGuards.clear();
        return hotModuleGuardPool.isShutdown();
    }

    public boolean addGuard(String platform,boolean isHotModule){
        FileCache fileCache = FileCacheManagerInstance.getInstance().getFileCache(HotModuleConfig.getFullFilePath());
        platform = platform.substring(0,1).toUpperCase() + platform.substring(1);
        String clazzName = platform+"Hot"+(isHotModule?"Module":"Live")+"LoadTask";
        String clazz = HotModuleConstPool.LOAD_TASK_CLASS_ROOT+"."+clazzName;
        String timeName = isHotModule?"updateHotModuleTimes":"updateHotLivesTimes";
        try {
            addGuard(new Guard(
                    clazzName.toLowerCase(),
                    (HotModuleLoadTask)Class.forName(clazz).getDeclaredConstructor().newInstance(),
                    (long)fileCache.get(timeName),
                    (int)fileCache.get("failRetryTimes")
            ));
        }catch (Exception e){
            return false;
        }
        return true;
    }
    public boolean addGuard(Guard guard){
        if(!runningGuards.containsKey(guard.getGuardName())){
           guardStart(guard);
           return true;
        }
        return false;
    }

    public boolean unActiveGuard(String guardName){
        if (runningGuards.containsKey(guardName)) {
            runningGuards.get(guardName).cancel(false);
            runningGuards.remove(guardName);
            return true;
        }
        return false;
    }

}
