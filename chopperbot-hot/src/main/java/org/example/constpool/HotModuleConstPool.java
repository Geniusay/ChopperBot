package org.example.constpool;

import org.apache.tomcat.util.bcel.Const;
import org.example.cache.FileCacheManagerInstance;
import org.example.config.HotModuleConfig;

/**
 * @author Genius
 * @date 2023/07/21 00:21
 **/
public class HotModuleConstPool {

    public static final String HOT_MODULE_CONFIG_ROOT = (String) GlobalFileCache.ModuleSrcConfigFile.get("src", ConstPool.HOT);  //热门模块配置文件路径

    public static final String LOAD_TASK_CLASS_ROOT = "org.example.core.loadtask.impl"; //各个平台爬虫任务包路径



}
