package org.example.init;

/**
 * @author Genius
 * @date 2023/07/21 00:18
 **/

import org.example.cache.FileCache;
import org.example.cache.FileCacheManagerInstance;
import org.example.config.CreeperLogConfigFile;
import org.example.config.HotModuleConfig;
import org.example.exception.FileCacheException;
import org.example.log.ChopperLogFactory;
import org.example.log.LoggerType;
import org.example.util.FileUtil;
import org.example.util.JsonFileUtil;
import org.slf4j.Logger;

import java.util.Map;

/**
 * 热门模块配置文件初始化机器
 */
public class HotModuleConfigInitMachine extends ConfigInitMachine<HotModuleConfig> {

    public HotModuleConfigInitMachine() {
        super(new HotModuleConfig(), ChopperLogFactory.getLogger(LoggerType.Hot));
    }

}
