package org.example.init;

import com.alibaba.fastjson.JSONObject;
import org.example.cache.FileCache;
import org.example.constpool.ConstPool;
import org.example.constpool.GlobalFileCache;
import org.example.constpool.ModuleName;
import org.example.constpool.PluginName;
import org.example.log.ChopperLogFactory;
import org.example.log.LoggerType;
import org.example.pojo.configfile.ChopperBotConfigFile;
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

    ChopperBotConfigFile chopperBotConfigFile;

    private boolean initFlag;

    public ChopperBotConfigFileInitMachine() {
        super(ModuleName.CHOPPER_BOT,ChopperLogFactory.getLogger(LoggerType.System));
        chopperBotConfigFile = new ChopperBotConfigFile();
        initFlag = true;
        isAutoStart = true;
        pluginName = PluginName.CHOPPER_BOT_CONFIG_PLUGIN;
        pluginName_CN = "ChopperBot系统配置文件";
        pluginClass = ChopperBotConfigFile.class;
        plugin = chopperBotConfigFile;
    }



    @PostConstruct
    @Override
    public boolean init() {
        Path dir = Paths.get(chopperBotConfigFile.getFilePath());
        if (!createConfigDirectory(dir)) {
            initFlag = fail("./config directory");
            return initFlag;
        }
        if (!createConfigFile(dir)) {
            initFlag = fail(ChopperBotConfigFile.getFullPath());
            return initFlag;
        }
        if (!createModuleDirectory()) {
            initFlag =  fail("module directory");
            return initFlag;
        }
        registerPlugin();
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
        Path path = Paths.get(dir.toString(), chopperBotConfigFile.getFileName());
        try {
            if (!Files.exists(path)) {
                JsonFileUtil.writeJsonFile(path.toString(), chopperBotConfigFile.packageConfig());
                GlobalFileCache.ModuleSrcConfigFile = new FileCache(chopperBotConfigFile);
            }else{
                Map<String, Object> data = JsonFileUtil.readJsonFile(Paths.get(dir.toString(), chopperBotConfigFile.getFileName()).toString());
                chopperBotConfigFile.setData(data);
                GlobalFileCache.ModuleSrcConfigFile = new FileCache(chopperBotConfigFile);
            }

            //全局插件注册表插入
            InitPluginRegister.pluginStartSetting = JSONObject.parseObject(GlobalFileCache.ModuleSrcConfigFile.get("pluginStart").toString(),Map.class);
            InitPluginRegister.allPlugins.put(PluginName.CHOPPER_BOT_CONFIG_PLUGIN,this);
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
        logger.error("[Wilderness] Create {} fail!Please delete your config directory.",str);
    }

    public boolean isInitFlag() {
        return initFlag;
    }
}
