package org.example.init;

/**
 * @author Genius
 * @date 2023/07/21 00:18
 **/

import org.example.cache.FileCache;
import org.example.cache.FileCacheManagerInstance;
import org.example.config.HotModuleConfig;
import org.example.constpool.HotModuleConstPool;
import org.example.exception.FileCacheException;
import org.example.log.HotModuleLogger;
import org.example.util.FileUtil;
import org.example.util.JsonFileUtil;
import org.slf4j.Logger;

import java.nio.file.Paths;
import java.util.Map;

/**
 * 热门模块配置文件初始化机器
 */
public class HotModuleConfigInitMachine extends CommonInitMachine {

    private String filePath = HotModuleConfig.getFullFilePath();

    public HotModuleConfigInitMachine() {
        super(HotModuleLogger.logger);
    }

    @Override
    public boolean init() {
        HotModuleConfig hotModuleConfig = new HotModuleConfig();
        if(!FileUtil.isFileExist(filePath)){
            JsonFileUtil.writeJsonFile(filePath,hotModuleConfig.packageConfig());
            try {
                FileCache fileCache = new FileCache(hotModuleConfig);
                FileCacheManagerInstance.getInstance().addFileCache(fileCache);
            } catch (FileCacheException e) {
                return fail(e.toString());
            }
            return success(String.format("[✔]%s is created,{%s} plugin init success!",filePath,this.getClass().toString()));
        }else{
            Map<String, Object> data = JsonFileUtil.readJsonFile(filePath);
            hotModuleConfig.setData(data);
            try {
                FileCache fileCache = new FileCache(hotModuleConfig);
                FileCacheManagerInstance.getInstance().addFileCache(fileCache);
            } catch (FileCacheException e) {
                return fail(e.toString());
            }
        }
        return success(String.format("[✔]%s read in disk,{%s} plugin init success!",filePath,this.getClass().toString()));
    }
}
