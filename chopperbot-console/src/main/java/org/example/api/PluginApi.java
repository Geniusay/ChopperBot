package org.example.api;

import org.example.cache.FileCacheManagerInstance;
import org.example.constpool.GlobalFileCache;
import org.example.exception.FileCacheException;
import org.example.init.InitPluginRegister;
import org.springframework.stereotype.Component;

/**
 * @author Genius
 * @date 2023/09/11 13:18
 **/
@Component
public class PluginApi {

    public boolean switchPluginAutoStart(String pluginName,boolean isOpen){
        try {
            GlobalFileCache.ModuleSrcConfigFile.writeKeys(isOpen,"pluginStart",pluginName);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
