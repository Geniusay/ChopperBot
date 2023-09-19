package org.example.core.guard;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.example.cache.FileCache;
import org.example.cache.FileCacheManagerInstance;
import org.example.config.HotModuleConfig;
import org.example.bean.HotModuleSetting;
import org.example.constpool.ConstGroup;
import org.example.constpool.PluginName;
import org.example.core.creeper.loadtask.HotModuleLoadTask;
import org.example.core.manager.CreeperGroupCenter;
import org.example.core.manager.CreeperManager;
import org.example.init.InitPluginRegister;
import org.example.plugin.CommonPlugin;
import org.example.plugin.SpringBootPlugin;
import org.example.service.HotModuleSettingService;
import org.example.thread.NamedThreadFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author Genius
 * @date 2023/07/19 03:21
 **/
@Component
public class HotModuleGuard extends SpringBootPlugin {

    private List<Guard> guards; //热度监控守卫列表，用于初始化一开始的热度监控列表
    private ScheduledExecutorService hotModuleGuardPool;    //热度监控守卫 定时线程池
    private Map<String,ScheduledFuture> runningGuards;      // 运行的热度监控守卫

    @Resource
    HotModuleSettingService service;
    @Override
    public boolean init(){
        try {
            FileCache HotModuleFileCache = FileCacheManagerInstance.getInstance().getFileCache(HotModuleConfig.getFullFilePath());

            List<Guard> guards = new ArrayList<>();
            int guardNum = (Integer)HotModuleFileCache.get(PluginName.HOT_GUARD_PLUGIN,"GuardNum");
            Map<String, HotModuleSetting> map = new HashMap<>();
            CreeperManager plugin = InitPluginRegister.getPlugin(PluginName.CREEPER_MANAGER_PLUGIN, CreeperManager.class);
            if(plugin==null)return false;
            List<HotModuleSetting> allSetting = service.getAllSetting();
            for (HotModuleSetting hotModuleSetting : allSetting) {
                map.put(hotModuleSetting.getPlatform(),hotModuleSetting);
                String platform = hotModuleSetting.getPlatform();
                if(hotModuleSetting.isEnableHotModule()){
                    String groupName = platform.toLowerCase() + "_hot_module";
                    HotModuleLoadTask loadTask =  plugin.getLoadTask(groupName);
                    if(loadTask!=null){
                        guards.add(new Guard(this.logger,loadTask.getClass().getName(),loadTask,
                                hotModuleSetting.getUpdateHotModuleTimes(),hotModuleSetting.getFailRetryTimes()));
                    }else{
                        this.error(String.format("Unable to listen %s hot module,cause: invalid loadTask!", groupName));
                    }
                }
                if(hotModuleSetting.isEnableHotLive()){
                    String groupName = platform.toLowerCase() + "_hot_live";
                    HotModuleLoadTask loadTask =  plugin.getLoadTask(platform.toLowerCase()+"_hot_live");
                    if(loadTask!=null){
                        guards.add(new Guard(this.logger,loadTask.getClass().getName(),loadTask,
                                hotModuleSetting.getUpdateHotLivesTimes(),hotModuleSetting.getFailRetryTimes()));
                    }else{
                        this.error(String.format("Unable to listen %s hot live,cause: invalid loadTask!", groupName));
                    }
                }
            }
            this.guards = guards;
            this.hotModuleGuardPool =  Executors.newScheduledThreadPool(guardNum, new NamedThreadFactory("HotModuleGuard"));
            runningGuards = new ConcurrentHashMap<>();
            start();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return true;
    }

    private void guardStart(Guard guard){
        if(!runningGuards.containsKey(guard.getGuardName())){
            ScheduledFuture<?> scheduledFuture = hotModuleGuardPool.scheduleWithFixedDelay(
                    guard, 0, guard.getDelayTime(), TimeUnit.MILLISECONDS
            );
            runningGuards.put(guard.getGuardName(),scheduledFuture);
        }

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

    @Override
    public void shutdown() {
        close();
    }

    public boolean close(){
        hotModuleGuardPool.shutdown();
        runningGuards.clear();
        return hotModuleGuardPool.isShutdown();
    }

    public boolean addGuard(String platform,boolean isHotModule){
        FileCache fileCache = FileCacheManagerInstance.getInstance().getFileCache(HotModuleConfig.getFullFilePath());
        platform = platform.substring(0,1).toUpperCase() + platform.substring(1);
        String groupName = CreeperGroupCenter.getGroupName(platform.toLowerCase(),isHotModule? ConstGroup.HOT_MODULE:ConstGroup.HOT_LIVE);

        String timeName = isHotModule?"updateHotModuleTimes":"updateHotLivesTimes";
        try {
            CreeperManager plugin = (CreeperManager) InitPluginRegister.getPlugin(PluginName.CREEPER_MANAGER_PLUGIN);
            if(plugin==null)return false;
            HotModuleLoadTask loadTask = plugin.getLoadTask(groupName);
            if(loadTask!=null){
                addGuard(new Guard(this.logger,loadTask.getClass().getName(),loadTask,
                        (long)fileCache.get(timeName),(int)fileCache.get("failRetryTimes")));
            }else{

                this.error(String.format("Unable to listen %s hot %s,cause: invalid loadTask!",
                        groupName,isHotModule?"module":"live"));

            }
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
