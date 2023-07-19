package org.example.config;

import org.example.bean.ConfigFile;
import org.example.constpool.ConstPool;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Genius
 * @date 2023/07/20 00:20
 **/
public class HotModuleConfig extends ConfigFile<Map<String,Object>> {
    private static final FollowDog allLivesDog = new FollowDog(FollowDog.ALL_LIVES,6,new ArrayList<>());
    private static final int FiveMinute = 0x493E0;

    private static final String filePath = "./config";

    private static final String fileName = "hotmodule.config";

    public HotModuleConfig(){
        super(filePath,fileName,
                Map.of("Enable", 1,
                        "Module", List.of(
                        new ModuleSetting(ConstPool.DOUYU, true, new ArrayList<>(),
                                false, List.of(allLivesDog), FiveMinute, FiveMinute),
                        new ModuleSetting(ConstPool.BILIBILI, true, new ArrayList<>(),
                                false, List.of(allLivesDog), FiveMinute, FiveMinute),
                        new ModuleSetting(ConstPool.HUYA, true, new ArrayList<>(),
                                false, List.of(allLivesDog), FiveMinute, FiveMinute),
                        new ModuleSetting(ConstPool.DOUYU, true, new ArrayList<>(),
                                false, List.of(allLivesDog), FiveMinute, FiveMinute)
                        ),
                        "GuardNum",10
                )
        );
    }

    public static String getFullFilePath(){
        return Paths.get(filePath,fileName).toString();
    }
}
