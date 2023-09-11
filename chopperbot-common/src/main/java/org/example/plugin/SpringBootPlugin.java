package org.example.plugin;

import org.example.log.ChopperLogFactory;
import org.slf4j.Logger;

import java.util.List;

/**
 * @author Genius
 * @date 2023/09/09 23:31
 **/
public abstract class SpringBootPlugin extends CommonPlugin {


    public SpringBootPlugin() {
        super("unknown", "unknown", null, false);
    }

    public void setEnv(List<String> needPlugins, boolean isAutoStart, String moduleName, String name, Logger logger){
        this.needPlugins = needPlugins;
        this.isAutoStart = isAutoStart;
        this.module = moduleName;
        this.pluginName = name;
        this.logger = logger;
    }
}
