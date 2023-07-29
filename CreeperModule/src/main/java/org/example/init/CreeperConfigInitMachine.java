package org.example.init;

import org.example.config.CreeperConfigFile;
import org.example.constpool.CreeperModuleConstPool;
import org.example.constpool.PluginName;
import org.example.log.ChopperLogFactory;
import org.example.log.LoggerType;
import org.example.util.FileUtil;
import org.slf4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Genius
 * @date 2023/07/29 01:56
 **/
public class CreeperConfigInitMachine extends ConfigInitMachine<CreeperConfigFile> {

    public CreeperConfigInitMachine() {
        super(PluginName.CREEPER_CONFIG_PLUGIN,
                new CreeperConfigFile(),
                ChopperLogFactory.getLogger(LoggerType.Creeper)
                );
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
