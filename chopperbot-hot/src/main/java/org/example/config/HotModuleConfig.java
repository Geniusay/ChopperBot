package org.example.config;

import org.example.bean.ConfigFile;
import org.example.bean.HotModuleSetting;
import org.example.constpool.CreeperModuleConstPool;
import org.example.constpool.HotModuleConstPool;
import org.example.constpool.PluginName;

import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

/**
 * 热门模块的整个配置类
 * 1，包含每个平台的热门爬取设置
 * 2，每个模块的热门推荐和自动推荐爬取设置
 * @author Genius
 * @date 2023/07/20 00:20
 **/
public class HotModuleConfig extends ConfigFile<Map<String,Object>> {
    private static final int FiveMinute = 0x493E0;
    private static final long OneDay = 0x5265C00;
    private static final String fileName = "hotModuleConfig.json";

    public HotModuleConfig(String module, String pluginName, List<String> needPlugins, boolean isAutoStart) {
        super(module, pluginName, needPlugins, isAutoStart
                ,HotModuleConstPool.HOT_MODULE_CONFIG_ROOT,fileName,
                Map.of(PluginName.HOT_GUARD_PLUGIN,Map.of("GuardNum",10),
                        PluginName.HOT_LIVER_FOLLOWER,Map.of("focusLive",1,
                                "focusBarrage",1,
                                "focusRecord",1,
                                "checkTime",60000)
                ));
    }


    public static String getFullFilePath(){
        return Paths.get(HotModuleConstPool.HOT_MODULE_CONFIG_ROOT,fileName).toString();
    }

}
