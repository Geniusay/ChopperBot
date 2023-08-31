package org.example.config;

import org.example.bean.ConfigFile;
import org.example.bean.FileType;
import org.example.constpool.ConstPool;
import org.example.constpool.CreeperModuleConstPool;
import org.example.constpool.PluginName;
import org.example.pool.LiveModuleConstPool;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Genius
 * @date 2023/09/01 00:49
 **/
public class LiveModuleConfigFile extends ConfigFile<Map<String,Object>> {
    private static final String filePath = LiveModuleConstPool.LIVE_MODULE_CONFIG_ROOT;

    private static final String fileName =  "liveConfig.json";


    public LiveModuleConfigFile(String module, String pluginName, List<String> needPlugins, boolean isAutoStart) {
        super(module, pluginName, needPlugins, isAutoStart
                ,filePath,fileName,
                Map.of("LiveDownload",Map.of("showDownloadTable",false))
        );
    }


    public static final String getFullPath(){
        return Paths.get(filePath,fileName).toString();
    }
}
