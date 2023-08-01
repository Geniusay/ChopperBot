package org.example.pojo.configfile;

import org.example.bean.ConfigFile;
import org.example.bean.FileType;
import org.example.constpool.ConstPool;
import org.example.constpool.PluginName;

import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

/**
 * @author Genius
 * @date 2023/04/20 19:29
 **/


public class ModuleSrcConfigFile extends ConfigFile<Map<String, Object>> {

    private static final String filePath = "./config/";

    private static final String fileName =  "chopperBotConfig.json";

    public ModuleSrcConfigFile() {
            super( "ChopperBot", PluginName.MODULE_CONFIG_PLUGIN,List.of(),true,filePath, fileName,
                    Map.of("src",Map.of( ConstPool.ACCOUNT, "./config/"+ConstPool.ACCOUNT,
                                    ConstPool.SECTION, "./config/"+ConstPool.SECTION,
                                    ConstPool.BARRAGE, "./config/"+ConstPool.BARRAGE,
                                    ConstPool.CREEPER, "./config/"+ConstPool.CREEPER,
                                    ConstPool.SECTION_WORK, "./config/"+ConstPool.SECTION_WORK,
                                    ConstPool.HOT, "./config/"+ConstPool.HOT,
                                    ConstPool.PUBLISH, "./config/"+ConstPool.PUBLISH),
                            "plugin",Map.of()), FileType.CHOPPER_BOT);
    }


    public Map<String,Object> packageConfig() {
        return super.packageConfig();
    }

    public static final String getFullPath(){
        return Paths.get(filePath,fileName).toString();
    }

}
