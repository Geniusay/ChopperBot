package org.example.constpool;

import org.example.cache.FileCacheManagerInstance;
import org.example.config.HotModuleConfig;

/**
 * @author Genius
 * @date 2023/07/21 00:21
 **/
public class HotModuleConstPool {

    public static final String HOT_MODULE_CONFIG_ROOT = (String) GlobalFileCache.ModuleSrcConfigFile.get("hot","src");  //热门模块配置文件路径

    public static final String LOAD_TASK_CLASS_ROOT = "org.example.core.control.hotmodule"; //各个平台爬虫任务包路径

}
