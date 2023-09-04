package org.example.cache;

import org.example.constpool.GlobalFileCache;
import org.example.constpool.PluginName;
import org.example.init.InitPluginRegister;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Genius
 * @date 2023/04/25 22:14
 **/

//FileCacheManager单例实体类
public class FileCacheManagerInstance {

    private static volatile FileCacheManager Instance;

    public static FileCacheManager getInstance(){
        if(Instance==null){
            synchronized (FileCacheManagerInstance.class){
                if(Instance==null){
                    Instance = InitPluginRegister.getPlugin(PluginName.HOT_RECOMMENDATION_PLUGIN, FileCacheManager.class);
                }
            }
        }
        return Instance;
    }

    public static void initInstance(FileCacheManager fileCacheManager){
        Instance = fileCacheManager;
        fileCacheManager.addFileCache(GlobalFileCache.ModuleSrcConfigFile);
    }
}
