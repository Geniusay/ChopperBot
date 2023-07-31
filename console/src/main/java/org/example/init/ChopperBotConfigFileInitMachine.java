package org.example.init;

import com.alibaba.fastjson.JSONObject;
import org.example.cache.FileCache;
import org.example.constpool.GlobalFileCache;
import org.example.constpool.PluginName;
import org.example.log.ChopperLogFactory;
import org.example.log.LoggerType;
import org.example.pojo.configfile.ModuleSrcConfigFile;
import org.example.util.FileUtil;
import org.example.util.JsonFileUtil;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

/**
 * @author Genius
 * @date 2023/04/20 18:34
 **/

@Component
public class ChopperBotConfigFileInitMachine extends CommonInitMachine {

    ModuleSrcConfigFile moduleSrcConfigFile;

    private boolean initFlag;

    public ChopperBotConfigFileInitMachine() {
        super( ChopperLogFactory.getLogger(LoggerType.System),
                PluginName.MODULE_CONFIG_PLUGIN);
        moduleSrcConfigFile = new ModuleSrcConfigFile();
        initFlag = true;
    }



    @PostConstruct
    @Override
    public boolean init() {
        Path dir = Paths.get(moduleSrcConfigFile.getFilePath());
        if (!createConfigDirectory(dir)) {
            initFlag = fail("./config directory");
            return initFlag;
        }
        if (!createConfigFile(dir)) {
            initFlag = fail(ModuleSrcConfigFile.getFullPath());
            return initFlag;
        }
        if (!createModuleDirectory()) {
            initFlag =  fail("module directory");
            return initFlag;
        }
        return initFlag;
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
                GlobalFileCache.ModuleSrcConfigFile = new FileCache(moduleSrcConfigFile);
            }else{
                Map<String, Object> data = JsonFileUtil.readJsonFile(Paths.get(dir.toString(), moduleSrcConfigFile.getFileName()).toString());
                moduleSrcConfigFile.setData(data);
                GlobalFileCache.ModuleSrcConfigFile = new FileCache(moduleSrcConfigFile);
            }
            InitWorld.pluginSetting = JSONObject.parseObject(GlobalFileCache.ModuleSrcConfigFile.get("plugin").toString(),Map.class);
        }catch (Exception e) {
            return false;
        }
        return true;
    }

    private boolean createModuleDirectory() {

        Map<String, Object> moduleSrcConfigFileMap = JSONObject.parseObject(GlobalFileCache.ModuleSrcConfigFile.get("src").toString());

        for (Map.Entry<String, Object> stringModuleConfigSrcEntry : moduleSrcConfigFileMap.entrySet()) {

            String src = stringModuleConfigSrcEntry.getValue().toString();
            try {
                if (!FileUtil.isFileExist(src)) {
                    Files.createDirectory(Path.of(src));
                }
            }catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean fail(String failCause) {
        failLog(failCause);
        return false;
    }

    @Override
    public void failLog(String str) {
        logger.error("[Wilderness] Create {} fail!",str);
    }

    public boolean isInitFlag() {
        return initFlag;
    }
}
