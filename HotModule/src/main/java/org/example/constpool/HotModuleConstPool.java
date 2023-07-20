package org.example.constpool;

import org.example.cache.FileCacheManagerInstance;
import org.example.config.HotModuleConfig;

/**
 * @author Genius
 * @date 2023/07/21 00:21
 **/
public class HotModuleConstPool {
    public static final String HOT_MODULE_CONFIG_ROOT = (String) GlobalFileCache.ModuleSrcConfigFile.get("hot","src");

}
