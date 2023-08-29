package org.example.core.guard;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.example.cache.FileCache;
import org.example.cache.FileCacheManagerInstance;
import org.example.config.HotModuleConfig;
import org.example.config.HotModuleSetting;
import org.example.constpool.PluginName;
import org.example.core.creeper.loadtask.HotModuleLoadTask;
import org.example.core.manager.CreeperManager;
import org.example.init.InitPluginRegister;
import org.example.log.ChopperLogFactory;
import org.example.log.LoggerType;
import org.example.plugin.CommonPlugin;
import org.example.thread.NamedThreadFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author Genius
 * @date 2023/07/19 03:21
 **/
public class HotModuleGuard extends CommonPlugin {

    private List<Guard> guards; //热度监控守卫列表，用于初始化一开始的热度监控列表
    private ScheduledExecutorService hotModuleGuardPool;    //热度监控守卫 定时线程池
    private Map<String,ScheduledFuture> runningGuards;      // 运行的热度监控守卫
    public HotModuleGuard(String module, String pluginName, List<String> needPlugins, boolean isAutoStart) {
        super(module, pluginName, needPlugins, isAutoStart);
    }

    @Override
    public boolean init() {
        try {
            FileCache HotModuleFileCache = FileCacheManagerInstance.getInstance().getFileCache(HotModuleConfig.getFullFilePath());

            List<Guard> guards = new ArrayList<>();
            int guardNum = (Integer)HotModuleFileCache.get("GuardNum");
            Map<String, HotModuleSetting> map = new HashMap<>();
            JSONArray modules = (JSONArray)HotModuleFileCache.get("Module");
            CreeperManager plugin = (CreeperManager) InitPluginRegister.getPlugin(PluginName.CREEPER_MANAGER_PLUGIN);
            if(plugin==null)return false;

            LoggerType type = ChopperLogFactory.nameToType.get(getModule());
            for (Object module : modules) {
                HotModuleSetting hotModuleSetting = JSONObject.parseObject(module.toString(), HotModuleSetting.class);
                map.put(hotModuleSetting.getPlatform(),hotModuleSetting);
                String platform = hotModuleSetting.getPlatform();
                if(hotModuleSetting.isEnableHotModule()){
                    String creeperName = platform.toLowerCase()+"_hot_module";
                    if (plugin.hasLoadTask(creeperName)) {
                        HotModuleLoadTask loadTask = plugin.getLoadTask(creeperName);
                        guards.add(new Guard(ChopperLogFactory.getLogger(type),loadTask.getClass().getName(),loadTask,
                                hotModuleSetting.getUpdateHotModuleTimes(),hotModuleSetting.getFailRetryTimes()));
                    }
                }
                if(hotModuleSetting.isEnableHotLive()){
                    String creeperName = platform.toLowerCase()+"_hot_live";
                    if (plugin.hasLoadTask(creeperName)) {
                        HotModuleLoadTask loadTask = plugin.getLoadTask(creeperName);
                        guards.add(new Guard(ChopperLogFactory.getLogger(type),loadTask.getClass().getName(),loadTask,
                                hotModuleSetting.getUpdateHotModuleTimes(),hotModuleSetting.getFailRetryTimes()));
                    }
                }
            }

            this.guards = guards;
            this.hotModuleGuardPool =  Executors.newScheduledThreadPool(guardNum, new NamedThreadFactory("HotModuleGuard"));
            runningGuards = new ConcurrentHashMap<>();
            start();
        }catch (Exception e){
            return false;
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
        String creeperName = platform.toLowerCase()+"_hot_"+(isHotModule?"module":"live");


        String timeName = isHotModule?"updateHotModuleTimes":"updateHotLivesTimes";
        try {
            CreeperManager plugin = (CreeperManager) InitPluginRegister.getPlugin(PluginName.CREEPER_MANAGER_PLUGIN);
            if(plugin==null)return false;
            if (plugin.hasLoadTask(creeperName)) {
                HotModuleLoadTask loadTask = plugin.getLoadTask(creeperName);
                addGuard(new Guard(ChopperLogFactory.getLogger(LoggerType.Hot),loadTask.getClass().getName(),loadTask,
                        (long)fileCache.get(timeName),(int)fileCache.get("failRetryTimes")));
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
