package org.example.init;

import org.example.bean.ConfigFile;
import org.example.plugin.CommonPlugin;
import org.example.plugin.Plugin;
import org.example.util.ConfigFileUtil;
import org.slf4j.Logger;

import java.nio.file.Paths;
import java.util.List;

/**
 * @author Genius
 * @date 2023/07/29 00:08
 **/
public abstract class ConfigInitMachine extends CommonInitMachine{

    private ConfigFile configFile;



    public ConfigInitMachine(List<String> needPlugins, boolean isAutoStart, String moduleName, String name, Class<? extends CommonPlugin> clazz) {
        super(needPlugins, isAutoStart, moduleName, name, clazz);
    }

    @Override
    public boolean init() {
        Plugin ano = this.getClass().getAnnotation(Plugin.class);
        try {
            configFile = (ConfigFile) ano.pluginClass()
                    .getDeclaredConstructor(String.class,String.class,List.class,boolean.class)
                    .newInstance(moduleName,pluginName,needPlugins,isAutoStart);
            if (ConfigFileUtil.createConfigFile(Paths.get(configFile.getFilePath(), configFile.getFileName()).toString(),configFile,logger,pluginName)) {
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            return fail(e.getMessage());
        }

    }


}
