package org.example.init;

import org.example.pojo.configfile.ModuleSrcConfigFile;
import org.example.util.FileUtil;
import org.example.util.JsonFileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

/**
 * @author Genius
 * @date 2023/04/20 18:34
 **/

public class ModuleSrcConfigFileInit implements InitMachine {

    private Logger logger = LoggerFactory.getLogger(ModuleSrcConfigFileInit.class);

    ModuleSrcConfigFile moduleSrcConfigFile;
    public ModuleSrcConfigFileInit() {
         moduleSrcConfigFile = new ModuleSrcConfigFile();
    }
    @Override
    public boolean init() {
        Path dir = Paths.get(moduleSrcConfigFile.getFilePath());
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
        Path path = Paths.get(dir.toString(), moduleSrcConfigFile.getFileName());
        try {
            if (!Files.exists(path)) {
                JsonFileUtil.writeJsonFile(path.toString(),moduleSrcConfigFile.packageConfig());
                logger.info("创建 {} 配置文件成功 √",moduleSrcConfigFile.getFileName());
            }
        }catch (Exception e) {
            logger.error("创建配置文件失败");
            return false;
        }
        return true;
    }

    private boolean createModuleDirectory() {

        Map<String, ModuleSrcConfigFile.SRC> moduleSrcConfigFileMap
                = (Map<String, ModuleSrcConfigFile.SRC>) moduleSrcConfigFile.packageConfig().get("data");

        for (Map.Entry<String, ModuleSrcConfigFile.SRC> stringModuleConfigSrcEntry : moduleSrcConfigFileMap.entrySet()) {

            ModuleSrcConfigFile.SRC src =  stringModuleConfigSrcEntry.getValue();
            try {
                if (!FileUtil.isFileExist(src.getSrc())) {
                    Files.createDirectory(Path.of(src.getSrc()));
                    logger.info("创建 {} 模块文件夹成功 √ ",src.getSrc());
                }
            }catch (Exception e) {
                logger.error("创建 {} 模块文件夹失败 ×",src.getSrc());
                return false;
            }
        }
        return true;
    }
}
