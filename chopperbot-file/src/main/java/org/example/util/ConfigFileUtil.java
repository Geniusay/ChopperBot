package org.example.util;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import org.example.bean.ConfigFile;
import org.example.cache.FileCache;
import org.example.cache.FileCacheManager;
import org.example.cache.FileCacheManagerInstance;
import org.example.constpool.PluginName;
import org.example.exception.FileCacheException;
import org.example.init.InitPluginRegister;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
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

    public static void changeSetting(Map<String,Object> settings,String filePath,String...prefixParams){
        FileCacheManager plugin = InitPluginRegister.getPlugin(PluginName.FILE_CACHE_PLUGIN, FileCacheManager.class);
        FileCache fileCache = plugin.getFileCache(filePath);
        settings.forEach(
                (k,v)->{
                    try {
                        if(ObjectUtils.isNotEmpty(v)){
                            ArrayList<String> list = new ArrayList<String>(List.of(prefixParams));
                            list.add(k);
                            fileCache.writeKeys(v, list.toArray(new String[0]));
                        }
                    } catch (InterruptedException | FileCacheException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }

    public static Object getSetting(String filePath,String...prefixParams){
        FileCacheManager plugin = InitPluginRegister.getPlugin(PluginName.FILE_CACHE_PLUGIN, FileCacheManager.class);
        FileCache fileCache = plugin.getFileCache(filePath);
        return fileCache.get(prefixParams);
    }
}
