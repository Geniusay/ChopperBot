package org.example.init;

import org.example.cache.FileCacheManager;
import org.example.cache.FileCacheManagerInstance;
import org.example.constpool.ConstPool;
import org.example.constpool.PluginName;
import org.example.log.ChopperLogFactory;
import org.example.log.LoggerType;
import org.example.plugin.CommonPlugin;
import org.example.plugin.Plugin;

import java.util.List;

/**
 * @author Genius
 * @date 2023/04/26 02:09
 **/

@Plugin(moduleName = ConstPool.FILE,
        pluginName = PluginName.FILE_CACHE_PLUGIN,
        needPlugin = {},
        pluginClass= FileCacheManager.class )
public class FileCacheManagerInitMachine extends CommonInitMachine{


    public FileCacheManagerInitMachine(List<String> needPlugins, boolean isAutoStart, String moduleName, String name, Class<? extends CommonPlugin> clazz) {
        super(needPlugins, isAutoStart, moduleName, name, clazz);
    }

    @Override
    public boolean init() {
        Plugin ano = this.getClass().getAnnotation(Plugin.class);
        try {
            plugin =  ano.pluginClass()
                    .getDeclaredConstructor(String.class,String.class,List.class,boolean.class)
                    .newInstance(moduleName,pluginName,needPlugins,isAutoStart);
            if (plugin.init()) {
                FileCacheManagerInstance.initInstance((FileCacheManager) plugin);
                FileCacheManagerInstance.getInstance().start();
                return success();
            }else{
                return fail();
            }
        }catch (Exception e){
            return fail(e.getMessage());
        }
    }

}
