package org.example.core.focus;

import lombok.AllArgsConstructor;
import org.example.bean.FocusLiver;
import org.example.bean.Live;
import org.example.cache.FileCache;
import org.example.cache.FileCacheManager;
import org.example.config.HotModuleConfig;
import org.example.constpool.ConstPool;
import org.example.constpool.PluginName;
import org.example.core.loadconfig.LoadConfig;
import org.example.core.loadtask.LoadTask;
import org.example.core.manager.AbstractCreeperGroup;
import org.example.core.manager.CreeperBuilder;
import org.example.core.manager.CreeperGroupCenter;
import org.example.core.manager.CreeperManager;
import org.example.core.taskcenter.TaskCenter;
import org.example.core.taskcenter.request.ReptileRequest;
import org.example.init.InitPluginRegister;
import org.example.plugin.CommonPlugin;
import org.example.plugin.PluginCheckAndDo;
import org.example.plugin.SpringBootPlugin;
import org.example.service.FocusLiverService;
import org.example.thread.NamedThreadFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author Genius
 * @date 2023/09/09 23:20
 **/
@Component
public class LiverFollower extends SpringBootPlugin {

    @Resource
    private FocusLiverService service;

    private boolean focusLive = true;
    private boolean focusBarrage = true;
    private boolean focusRecord = true;

    private long checkTime = 60000;

    List<FocusLiver> focusLivers;

    private ScheduledExecutorService focusPool;

    private Map<String, Future> focusFuture;

    @Override
    public boolean init() {
        try {
            FileCacheManager plugin = InitPluginRegister.getPlugin(PluginName.FILE_CACHE_PLUGIN, FileCacheManager.class);
            assert plugin != null;
            FileCache fileCache = plugin.getFileCache(HotModuleConfig.getFullFilePath());
            focusLive = (Integer)fileCache.get("LiverFollower", "focusLive")==1;
            focusBarrage = (Integer)fileCache.get("LiverFollower", "focusBarrage")==1;
            focusRecord = (Integer)fileCache.get("LiverFollower", "focusRecord")==1;
            checkTime = (Integer)fileCache.get("LiverFollower", "checkTime");
            focusLivers = service.getFocusLivers();
            info(String.format("Find %s following anchors", focusLivers.size()));
            focusFuture = new HashMap<>();
            focusPool = Executors.newScheduledThreadPool(focusLivers.size()+5, new NamedThreadFactory(pluginName));
            doFocus();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return true;
    }

    private void doFocus(){
        focusLivers.forEach(
                liver -> {
                    String function = "focus_check";
                    String platform = liver.getPlatform();
                    String groupName = CreeperGroupCenter.getGroupName(platform, function);
                    CreeperManager plugin = InitPluginRegister.getPlugin(PluginName.CREEPER_MANAGER_PLUGIN, CreeperManager.class);
                    assert plugin != null;
                    LoadTask loadTask = plugin.getLoadTask(groupName, liver);
                    if(loadTask!=null){
                        ScheduledFuture<?> schedule = focusPool.scheduleWithFixedDelay(new FollowerEyes(loadTask, platform, liver.getLiver()),0,
                                checkTime, TimeUnit.MILLISECONDS);
                        focusFuture.put(liver.getLiver(),schedule);
                        info(String.format("eyes on the liver:%s", liver.getLiver()));
                    }else{
                        error(String.format("cant found platform:%s %s creeper",platform,groupName));
                    }
                }
        );
    }


    @AllArgsConstructor
    class FollowerEyes implements Runnable,Serializable{

        private LoadTask checker;

        private String platform;
        private String liver;

        @Override
        public void run() {
            Object live = checker.start();
            if(live!=null){
                info(String.format("The %s is starting to live!!!!!!", liver));
                doLiveCreeper(live);
                doBarrageCreeper(live);
            }
        }

        public void doLiveCreeper(Object obj){
            if(focusLive){
                String groupName = CreeperGroupCenter.getGroupName(platform, "live");
                PluginCheckAndDo.CheckAndDo(
                        (plugin)->{
                            ((TaskCenter)plugin).request(
                                    new ReptileRequest((t)->{
                                        System.out.printf("主播%s直播结束\n", liver);
                                    },groupName,obj)
                            );
                        },PluginName.TASK_CENTER_PLUGIN
                );

            }
        }

        public void doBarrageCreeper(Object obj){
            if(focusBarrage){
                String groupName = CreeperGroupCenter.getGroupName(platform,"live_barrage");
                PluginCheckAndDo.CheckAndDo(
                        (plugin)->{
                            ((TaskCenter)plugin).request(
                                    new ReptileRequest((t)->{
                                        System.out.printf("主播%s弹幕爬取结束\n%n", liver);
                                    },groupName,obj)
                            );
                        },PluginName.TASK_CENTER_PLUGIN
                );
            }
        }
    }
}
