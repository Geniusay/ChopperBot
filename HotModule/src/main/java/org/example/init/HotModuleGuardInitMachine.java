package org.example.init;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.example.cache.FileCache;
import org.example.cache.FileCacheManagerInstance;
import org.example.config.HotModuleConfig;
import org.example.config.HotModuleSetting;
import org.example.core.control.HotModuleLoadTask;
import org.example.guard.HotModuleGuardInstance;
import org.example.guard.Guard;
import org.example.log.HotModuleLogger;
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
public class HotModuleGuardInitMachine extends CommonInitMachine{


    public HotModuleGuardInitMachine() {
        super(HotModuleLogger.logger);
    }

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
                        guards.add(new Guard(clazzName,task,
                                hotModuleSetting.getUpdateHotModuleTimes(),hotModuleSetting.getFailRetryTimes()));
                    }else if(hotModuleSetting.isEnableHotLive()){
                        HotModuleLoadTask task = (HotModuleLoadTask)loadClazz.getDeclaredConstructor().newInstance();
                        guards.add(new Guard(clazzName,task,
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
        try {
            envInit();
        } catch (Exception e) {
            return fail(e.getMessage());
        }
        return success();
    }

}
