package org.example.bean;

import org.example.common.ConfigFile;
import org.example.constpool.ConstPool;

import java.util.Map;

/**
 * @author Genius
 * @date 2023/04/20 19:29
 **/


public class ModuleSrcConfigFile extends ConfigFile<Map<String, ModuleSrcConfigFile.SRC>> {

    private static Map<String, SRC> config;

    public static class SRC{
        private String src;
        public SRC(String src) {
            this.src = src;
        }

        public String getSrc() {
            return src;
        }

        public void setSrc(String src){this.src =src;}
    }

    static{
        config = Map.of(
                ConstPool.ACCOUNT, new SRC("./config/"+ConstPool.ACCOUNT),
                ConstPool.SECTION, new SRC("./config/"+ConstPool.SECTION),
                ConstPool.BARRAGE, new SRC("./config/"+ConstPool.BARRAGE),
                ConstPool.CREEPER, new SRC("./config/"+ConstPool.CREEPER),
                ConstPool.SECTION_WORK, new SRC("./config/"+ConstPool.SECTION_WORK),
                ConstPool.HOT, new SRC("./config/"+ConstPool.HOT),
                ConstPool.PUBLISH, new SRC("./config/"+ConstPool.PUBLISH)
        );
    }


    public ModuleSrcConfigFile() {
        super("E:\\Project\\ChopperBot\\config\\"
                , "moduleConfig.json"
                , config);
    }

    public Map<String,Object> packageConfig() {
        return super.packageConfig();
    }

}
