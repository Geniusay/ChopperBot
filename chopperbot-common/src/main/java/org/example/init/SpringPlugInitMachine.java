package org.example.init;

import org.example.log.ChopperLogFactory;
import org.example.plugin.CommonPlugin;
import org.example.plugin.SpringBootPlugin;

import java.util.List;

/**
 * @author Genius
 * @date 2023/09/09 23:58
 **/

public abstract class SpringPlugInitMachine extends CommonInitMachine{

    public SpringPlugInitMachine(){
        super(null, false, "unknown", "unknown", null);
    }

    public void setEnv(List<String> needPlugins, boolean isAutoStart, String moduleName, String name, Class<? extends CommonPlugin> clazz){
        this.needPlugins = needPlugins;
        this.isAutoStart = isAutoStart;
        this.moduleName = moduleName;
        this.pluginName = name;
        this.pluginClass = clazz;
        setLogger(ChopperLogFactory.getLogger(moduleName));
    }

    public void setPlugin(SpringBootPlugin plugin){
        plugin.setEnv(needPlugins,isAutoStart,moduleName,pluginName,logger);
        this.plugin = plugin;
    }


    @Override
    public boolean init(){
        if (plugin.init()) {
            return success();
        }else{
            return fail();
        }
    }
}
