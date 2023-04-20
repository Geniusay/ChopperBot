package org.example.init;

import org.example.bean.ModuleConfigSrc;
import org.example.util.FileUtil;
import org.example.util.JsonFileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;

/**
 * @author Genius
 * @date 2023/04/20 18:34
 **/

public class ModuleConfigSrcInit implements InitMachine {

    private Logger logger = LoggerFactory.getLogger(ModuleConfigSrcInit.class);
    private static final Map<String, ModuleConfigSrc> config;

    static{
         config = Map.of(
                "account", new ModuleConfigSrc("./config/account"),
                "section", new ModuleConfigSrc("./config/section"),
                "barrage", new ModuleConfigSrc("./config/barrage"),
                "creeper", new ModuleConfigSrc("./config/creeper"),
                "videowork", new ModuleConfigSrc("./config/videowork"),
                "hot", new ModuleConfigSrc("./config/hot"),
                "publish", new ModuleConfigSrc("./config/publish")
        );
    }

    private static final String CONFIG_SRC = "./config";
    private static final String CONFIG_SRC_FILE = "moduleConfig.json";

    // 初始化每个模块的配置文件夹
    @Override
    public boolean init() {
        Path dir = Paths.get(CONFIG_SRC);
        if (!createConfigDirectory(dir)) {
            return false;
        }
        if (!createConfigFile(dir)) {
            return false;
        }
        if (!createModuleDirectory()) {
            return false;
        }
        return true;
    }

    private boolean createConfigDirectory(Path dir) {
        try {
            if (!Files.exists(dir)) {
                Files.createDirectory(dir);
                logger.info("创建 config 文件夹成功 √ ");
            }
        }catch (Exception e) {
            logger.error("创建配置文件夹失败");
            return false;
        }
        return true;
    }

    private boolean createConfigFile(Path dir) {
        Path path = Paths.get(dir.toString(), CONFIG_SRC_FILE);
        try {
            if (!Files.exists(path)) {
                JsonFileUtil.writeJsonFile(path.toString(),config);
                logger.info("创建 {} 配置文件成功 √",CONFIG_SRC_FILE);
            }
        }catch (Exception e) {
            logger.error("创建配置文件失败");
            return false;
        }
        return true;
    }

    private boolean createModuleDirectory() {
        for (Map.Entry<String, ModuleConfigSrc> stringModuleConfigSrcEntry : config.entrySet()) {
            ModuleConfigSrc moduleConfigSrc = stringModuleConfigSrcEntry.getValue();
            try {
                if (!FileUtil.isFileExist(moduleConfigSrc.getSrc())) {
                    Files.createDirectory(Path.of(moduleConfigSrc.getSrc()));
                    logger.info("创建 {} 模块文件夹成功 √ ",moduleConfigSrc.getSrc());
                }
            }catch (Exception e) {
                logger.error("创建 {} 模块文件夹失败 ×",moduleConfigSrc.getSrc());
                return false;
            }
        }
        return true;
    }
}
