package org.example.guard;

import org.example.cache.FileCacheManager;
import org.example.cache.FileCacheManagerInstance;
import org.example.config.HotModuleConfig;
import org.example.core.control.impl.DouyuHotLiveLoadTask;
import org.example.core.control.impl.DouyuHotModuleLoadTask;
import org.example.core.processor.hotmodule.DouyuHotLiveProcessor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Genius
 * @date 2023/07/19 03:21
 **/
public class HotModuleGuard {

    private static final long delayTime = 10*1000;
    private ScheduledExecutorService hotModuleGuardPool = Executors.newScheduledThreadPool(
            (Integer)FileCacheManagerInstance.getInstance().getFileCache(HotModuleConfig.getFullFilePath()).get("GuardNum")
    );

    public void start(){
        hotModuleGuardPool.scheduleWithFixedDelay(()-> {
            new DouyuHotLiveLoadTask().start();
        },0,delayTime, TimeUnit.MILLISECONDS);

        hotModuleGuardPool.scheduleWithFixedDelay(()->{
            new DouyuHotModuleLoadTask().start();
        },0,delayTime,TimeUnit.MILLISECONDS);
    }

    public static void main(String[] args) {
        new HotModuleGuard().start();
    }
}
