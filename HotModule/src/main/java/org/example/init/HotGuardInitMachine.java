package org.example.init;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.example.cache.FileCache;
import org.example.cache.FileCacheManagerInstance;
import org.example.config.HotModuleConfig;
import org.example.config.HotModuleSetting;
import org.example.constpool.PluginName;
import org.example.core.control.HotModuleLoadTask;
import org.example.core.guard.HotModuleGuardInstance;
import org.example.core.guard.Guard;
import org.example.log.ChopperLogFactory;
import org.example.log.LoggerType;
import org.example.util.ClassUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.example.constpool.HotModuleConstPool.LOAD_TASK_CLASS_ROOT;

/**
 * @author Genius
 * @date 2023/07/21 09:58
 **/
public class HotGuardInitMachine extends CommonInitMachine{


    public HotGuardInitMachine() {
        super(  List.of(PluginName.HOT_CONFIG_PLUGIN,
                        PluginName.FILE_CACHE_PLUGIN),

                ChopperLogFactory.getLogger(LoggerType.Hot),
                PluginName.HOT_GUARD_PLUGIN);
    }

    /**
     * 初始化整个热度监控的环境
     * 1，获取热度监控的所有配置，并进行读取和初始化
     * 2，获得所有守卫并进行初始化
     * 3，初始化热度监控插件
     * @throws Exception
     */
    private void envInit() throws Exception {
        FileCache HotModuleFileCache = FileCacheManagerInstance.getInstance().getFileCache(HotModuleConfig.getFullFilePath());

        List<Guard> guards = new ArrayList<>();
        int guardNum = (Integer)HotModuleFileCache.get("GuardNum");
        Map<String,HotModuleSetting> map = new HashMap<>();
        JSONArray modules = (JSONArray)HotModuleFileCache.get("Module");
        for (Object module : modules) {
            HotModuleSetting hotModuleSetting = JSONObject.parseObject(module.toString(), HotModuleSetting.class);
            map.put(hotModuleSetting.getPlatform(),hotModuleSetting);
        }

        for (String clazz : ClassUtil.getClassesInPackage(LOAD_TASK_CLASS_ROOT)) {
            String[] split = clazz.split("\\.");
            String clazzName = split[split.length-1].toLowerCase();
            if(clazzName.endsWith("loadtask")&& clazzName.contains("hot")){
                String platformName = clazzName.split("hot")[0];
                boolean isHotModule = clazzName.contains("module");
                if(map.containsKey(platformName)){
                    HotModuleSetting hotModuleSetting = map.get(platformName);
                    Class<?> loadClazz = Class.forName(clazz);
                    if(isHotModule&&hotModuleSetting.isEnableHotModule()){
                        HotModuleLoadTask task = (HotModuleLoadTask)loadClazz.getDeclaredConstructor().newInstance();
                        guards.add(new Guard(logger,clazzName,task,
                                hotModuleSetting.getUpdateHotModuleTimes(),hotModuleSetting.getFailRetryTimes()));
                    }else if(hotModuleSetting.isEnableHotLive()){
                        HotModuleLoadTask task = (HotModuleLoadTask)loadClazz.getDeclaredConstructor().newInstance();
                        guards.add(new Guard(logger,clazzName,task,
                                hotModuleSetting.getUpdateHotLivesTimes(),hotModuleSetting.getFailRetryTimes()));
                    }
                }
            }
        }
        HotModuleGuardInstance.InitInstance(guards,guardNum);
        HotModuleGuardInstance.getInstance().start();
    }

    @Override
    public boolean init() {
        if(checkNeedPlugin()){
            try {
                envInit();
            } catch (Exception e) {
                return fail(e.getMessage());
            }
            registerPlugin();
            return success();
        }
        return false;
    }

    @Override
    public void shutdown() {
        super.shutdown();
        HotModuleGuardInstance.getInstance().close();
    }
}
