package org.example.init;

import org.example.config.CreeperConfigFile;
import org.example.constpool.ConstPool;
import org.example.constpool.CreeperModuleConstPool;
import org.example.constpool.ModuleName;
import org.example.constpool.PluginName;
import org.example.plugin.CommonPlugin;
import org.example.plugin.annotation.Plugin;
import org.example.util.FileUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author Genius
 * @date 2023/07/29 01:56
 **/

@Plugin(moduleName = ModuleName.CREEPER,
        pluginName = PluginName.CREEPER_CONFIG_PLUGIN,
        needPlugin = {PluginName.FILE_CACHE_PLUGIN},
        pluginClass= CreeperConfigFile.class )
public class CreeperConfigInitMachine extends ConfigInitMachine {


    public CreeperConfigInitMachine(List<String> needPlugins, boolean isAutoStart, String moduleName, String name, Class<? extends CommonPlugin> clazz) {
        super(needPlugins, isAutoStart, moduleName, name, clazz);
    }

    @Override
    public boolean init() {
        try {
            Path path = Paths.get(CreeperModuleConstPool.CREEPER_ROOT, "log");
            if(!FileUtil.isFileExist(path.toString())){
                Files.createDirectory(path);
            }
        }catch (IOException e) {
           return false;
        }
        return super.init();
    }
}
