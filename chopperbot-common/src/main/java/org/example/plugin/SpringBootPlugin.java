package org.example.plugin;


import org.example.sql.SQLInitMachine;
import org.example.sql.annotation.SQLInit;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Genius
 * @date 2023/09/09 23:31
 **/
@Component
public abstract class SpringBootPlugin extends CommonPlugin implements SQLInitMachine {


    public SpringBootPlugin() {
        super("unknown", "unknown", null, false);
    }


    @Override
    public boolean init() {
        return super.init();
    }

    public void setEnv(List<String> needPlugins, boolean isAutoStart, String moduleName, String name, Logger logger){
        this.needPlugins = needPlugins;
        this.isAutoStart = isAutoStart;
        this.module = moduleName;
        this.pluginName = name;
        this.logger = logger;
    }

    @Override
    public List<?> sqlInit() {
        return null;
    }
}
