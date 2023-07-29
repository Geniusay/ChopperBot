package org.example.init;

import org.example.constpool.PluginName;
import org.example.log.ChopperLogFactory;
import org.example.log.LoggerType;
import org.example.pojo.configfile.ModuleSrcConfigFile;
import org.example.util.FileUtil;
import org.example.util.JsonFileUtil;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

/**
 * @author Genius
 * @date 2023/04/20 18:34
 **/

public class ModuleSrcConfigFileInit extends CommonInitMachine {

    ModuleSrcConfigFile moduleSrcConfigFile;

    public ModuleSrcConfigFileInit() {
        super( ChopperLogFactory.getLogger(LoggerType.File),
                PluginName.MODULE_CONFIG_PLUGIN);
        moduleSrcConfigFile = new ModuleSrcConfigFile();
    }

    @Override
    public boolean init() {
        Path dir = Paths.get(moduleSrcConfigFile.getFilePath());
        if (!createConfigDirectory(dir)) {
            return fail("创建Config目录失败");
        }
        if (!createConfigFile(dir)) {
            return fail("创建Config文件失败");
        }
        if (!createModuleDirectory()) {
            return fail("创建模块文件夹失败");
        }
        return success();
    }

    private boolean createConfigDirectory(Path dir) {
        try {
            if (!Files.exists(dir)) {
                Files.createDirectory(dir);
            }
        }catch (Exception e) {
            return false;
        }
        return true;
    }

    private boolean createConfigFile(Path dir) {
        Path path = Paths.get(dir.toString(), moduleSrcConfigFile.getFileName());
        try {
            if (!Files.exists(path)) {
                JsonFileUtil.writeJsonFile(path.toString(),moduleSrcConfigFile.packageConfig());
            }
        }catch (Exception e) {
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
                }
            }catch (Exception e) {
                return false;
            }
        }
        return true;
    }
}
