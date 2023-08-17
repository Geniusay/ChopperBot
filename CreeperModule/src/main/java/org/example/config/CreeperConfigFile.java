package org.example.config;

import org.example.bean.ConfigFile;
import org.example.bean.FileType;
import org.example.constpool.ConstPool;
import org.example.constpool.CreeperModuleConstPool;

import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

/**
 * @author Genius
 * @date 2023/07/28 23:54
 **/
public class CreeperConfigFile extends ConfigFile<Map<String,Object>> {

    private static final String filePath = CreeperModuleConstPool.CREEPER_ROOT;

    private static final String fileName = "creeperConfig.json";

    public CreeperConfigFile(String module, String pluginName, List<String> needPlugins, boolean isAutoStart) {

        super(module,pluginName,needPlugins,isAutoStart,filePath, fileName,
                Map.of("taskCenter",new TaskCenterConfig(10,50,1000),
                        "spiderConfig",Map.of(
                                ConstPool.PLATFORM.DOUYU.getName(),new SpiderConfig(),
                                ConstPool.PLATFORM.BILIBILI.getName(),new SpiderConfig()
                        )
                ),
                FileType.CREEPER);
    }



    public static String getFullFilePath(){
        return Paths.get(filePath,fileName).toString();
    }

}
