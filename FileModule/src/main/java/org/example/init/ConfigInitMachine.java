package org.example.init;

import org.example.bean.ConfigFile;
import org.example.cache.FileCache;
import org.example.cache.FileCacheManagerInstance;
import org.example.exception.FileCacheException;
import org.example.util.ConfigFileUtil;
import org.example.util.FileUtil;
import org.example.util.JsonFileUtil;
import org.slf4j.Logger;

import java.nio.file.Paths;
import java.util.Map;

/**
 * @author Genius
 * @date 2023/07/29 00:08
 **/
public abstract class ConfigInitMachine<T extends ConfigFile> extends CommonInitMachine{

    private T configFile;

    private String filePath;

    public ConfigInitMachine(T configFile, Logger logger) {
        super(logger);
        this.configFile = configFile;
        filePath = Paths.get(configFile.getFilePath(), configFile.getFileName()).toString();
    }



    @Override
    public boolean init() {
        return ConfigFileUtil.createConfigFile(filePath,configFile,logger,this.getClass().getName());
    }


}
