package org.example.util;

import org.example.bean.ConfigFile;
import org.example.cache.FileCache;
import org.example.cache.FileCacheManagerInstance;
import org.example.exception.FileCacheException;
import org.slf4j.Logger;

import java.util.Map;

/**
 * @author Genius
 * @date 2023/07/29 01:40
 **/
public class ConfigFileUtil {

    public static boolean createConfigFile(String filePath, ConfigFile configFile, Logger logger,String pluginName,boolean isNeedLog) {
        if(!FileUtil.isFileExist(filePath)){
            JsonFileUtil.writeJsonFile(filePath,configFile.packageConfig());
            try {
                FileCache fileCache = new FileCache(configFile);
                FileCacheManagerInstance.getInstance().addFileCache(fileCache);
            } catch (FileCacheException e) {
                if(isNeedLog) logger.error(e.toString());
                return false;
            }
            if(isNeedLog) logger.info(String.format("[✔]%s is created,{%s} plugin init success!", filePath, pluginName));
            return true;
        }else{
            Map<String, Object> data = JsonFileUtil.readJsonFile(filePath);
            configFile.setData(data);
            try {
                FileCache fileCache = new FileCache(configFile);
                FileCacheManagerInstance.getInstance().addFileCache(fileCache);
            } catch (FileCacheException e) {
                if(isNeedLog) logger.error(e.toString());
                return false;
            }
        }
        if(isNeedLog) logger.info(String.format("[✔]%s read in disk,{%s} plugin init success!", filePath, pluginName));
        return true;
    }

    public static boolean createConfigFile(String filePath, ConfigFile configFile, Logger logger,String pluginName){
        return createConfigFile(filePath,configFile,logger,pluginName,true);
    }

    public static boolean createConfigFile(String filePath, ConfigFile configFile){
        return createConfigFile(filePath,configFile,null,null,false);
    }
}
