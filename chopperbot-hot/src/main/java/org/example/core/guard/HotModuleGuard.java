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

    private CreeperManager creeperManagerPlugin;
    @Resource
    HotModuleSettingService service;
    @Override
    public boolean init(){
        try {
            FileCache HotModuleFileCache = FileCacheManagerInstance.getInstance().getFileCache(HotModuleConfig.getFullFilePath());
            int guardNum = (Integer)HotModuleFileCache.get(PluginName.HOT_GUARD_PLUGIN,"GuardNum");
            creeperManagerPlugin = InitPluginRegister.getPlugin(PluginName.CREEPER_MANAGER_PLUGIN, CreeperManager.class);
            if(creeperManagerPlugin==null)return false;
            List<HotModuleSetting> allSetting = service.getAllSetting();
            this.hotModuleGuardPool =  Executors.newScheduledThreadPool(guardNum, new NamedThreadFactory("HotModuleGuard"));
            runningGuards = new ConcurrentHashMap<>();
            this.guards = new CopyOnWriteArrayList<>();
            for (HotModuleSetting hotModuleSetting : allSetting) {
                addGuard(hotModuleSetting);
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return true;
    }

    private void guardStart(Guard guard){
        try {
            ScheduledFuture<?> scheduledFuture = hotModuleGuardPool.scheduleWithFixedDelay(
                    guard, 0, guard.getDelayTime(), TimeUnit.MILLISECONDS
            );
            guards.add(guard);
            runningGuards.put(guard.getGuardName(),scheduledFuture);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }


    public boolean addGuard(String platform){
        HotModuleSetting setting = service.getSetting(platform);
        return addGuard(setting);
    }

    public boolean addGuard(HotModuleSetting hotModuleSetting){
        String platform = hotModuleSetting.getPlatform();
        String groupName = CreeperGroupCenter.getGroupName(platform.toLowerCase(),ConstGroup.HOT_MODULE);;
        if(hotModuleSetting.getEnableHotModule()){
            HotModuleLoadTask loadTask =  creeperManagerPlugin.getLoadTask(groupName);
            if(loadTask!=null){
                addGuard(new Guard(this.logger,groupName,loadTask,
                        hotModuleSetting.getUpdateHotModuleTimes(),
                        hotModuleSetting.getFailRetryTimes()));
            }else{
                this.error(String.format("Unable to listen %s hot module,cause: invalid loadTask!", groupName));
                return false;
            }
        }else{
            unActiveGuard(groupName);
        }
        groupName = CreeperGroupCenter.getGroupName(platform.toLowerCase(),ConstGroup.HOT_LIVE);
        if(hotModuleSetting.getEnableHotLive()){
            HotModuleLoadTask loadTask =  creeperManagerPlugin.getLoadTask(groupName);
            if(loadTask!=null){
                addGuard(new Guard(this.logger,groupName,loadTask,
                        hotModuleSetting.getUpdateHotLivesTimes(),
                        hotModuleSetting.getFailRetryTimes()));
            }else{
                this.error(String.format("Unable to listen %s hot live,cause: invalid loadTask!", groupName));
                return false;
            }
        }else{
            unActiveGuard(groupName);
        }
        return true;
    }
    private boolean addGuard(Guard guard){
        if(!runningGuards.containsKey(guard.getGuardName())){
            guardStart(guard);
            return true;
        }
        return false;
    }
    public boolean unActiveGuard(String guardName){
        if (runningGuards.containsKey(guardName)) {
            runningGuards.get(guardName).cancel(true);
            runningGuards.remove(guardName);
            return true;
        }
        return false;
    }

    public List<Guard> getGuards(){
        return guards;
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


}
