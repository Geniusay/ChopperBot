package org.example.config;

import org.example.bean.ConfigFile;
import org.example.constpool.ConstPool;
import org.example.constpool.HotModuleConstPool;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Genius
 * @date 2023/07/20 00:20
 **/
public class HotModuleConfig extends ConfigFile<Map<String,Object>> {
    private static final int FiveMinute = 0x493E0;


    private static final String fileName = "hotModuleConfig.json";

    public HotModuleConfig(){
        super(HotModuleConstPool.HOT_MODULE_CONFIG_ROOT,fileName,
                Map.of("Enable", 1,
                        "Module", List.of(
                        new ModuleSetting(ConstPool.DOUYU, true, new ArrayList<>(), false,
                                List.of(allLiveDog()), FiveMinute, FiveMinute),
                        new ModuleSetting(ConstPool.BILIBILI, true, new ArrayList<>(), false,
                                List.of(allLiveDog()), FiveMinute, FiveMinute),
                        new ModuleSetting(ConstPool.HUYA, true, new ArrayList<>(), false,
                                List.of(allLiveDog()), FiveMinute, FiveMinute),
                        new ModuleSetting(ConstPool.DOUYING, true, new ArrayList<>(), false,
                                List.of(allLiveDog()), FiveMinute, FiveMinute)
                        ),
                        "GuardNum",10
                )
        );
    }

    public static String getFullFilePath(){
        return Paths.get(HotModuleConstPool.HOT_MODULE_CONFIG_ROOT,fileName).toString();
    }

    private static FollowDog allLiveDog(){
        return new FollowDog(FollowDog.ALL_LIVES,6,new ArrayList<>());
    }
}
