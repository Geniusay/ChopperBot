package org.example.init;

import org.example.config.LiveModuleConfigFile;
import org.example.constpool.ConstPool;
import org.example.constpool.ModuleName;
import org.example.constpool.PluginName;
import org.example.plugin.CommonPlugin;
import org.example.plugin.annotation.Plugin;
import org.example.pool.LiveModuleConstPool;
import org.example.util.FileUtil;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * @author Genius
 * @date 2023/09/01 01:13
 **/
@Plugin(moduleName = ModuleName.LIVE,
        pluginName = PluginName.LIVE_CONFIG_PLUGIN,
        pluginName_CN = "直播模块配置文件",
        needPlugin = {PluginName.FILE_CACHE_PLUGIN},
        pluginClass= LiveModuleConfigFile.class )
public class LiveConfigInitMachine extends ConfigInitMachine{

    public LiveConfigInitMachine(List<String> needPlugins, boolean isAutoStart, String moduleName, String name, Class<? extends CommonPlugin> clazz) {
        super(needPlugins, isAutoStart, moduleName, name, clazz);
    }

    @Override
    public boolean init() {
        String fullPath = LiveModuleConstPool.LIVE_MODULE_CONFIG_ROOT;
        String online = "online";
        String record = "record";
        try {
            Path onlinePath = Path.of(fullPath,online);
            Path recordPath = Path.of(fullPath,record);
            if(!FileUtil.isFileExist(onlinePath.toString())){
                Files.createDirectory(onlinePath);
            }
            if(!FileUtil.isFileExist(recordPath.toString())){
                Files.createDirectory(recordPath);
            }
            for (ConstPool.PLATFORM value : ConstPool.PLATFORM.values()) {
                String name = value.getName();
                Path onlinePlatformPath = Path.of(onlinePath.toString(),  name);
                Path recordPlatformPath = Path.of(recordPath.toString(), name);
                if(!FileUtil.isFileExist(onlinePlatformPath.toString())){
                    logger.info("[{}}] 构建文件夹 {}",pluginName,onlinePlatformPath);
                    Files.createDirectory(onlinePlatformPath);
                }
                if(!FileUtil.isFileExist(recordPlatformPath.toString())){
                    logger.info("[{}}] 构建文件夹 {}",pluginName,recordPlatformPath);
                    Files.createDirectory(recordPlatformPath);
                }
            }
        }catch (Exception e){
            return fail(e.getMessage());
        }
        return super.init();
    }
}
