package org.example.init;

import org.example.cache.FileCacheManager;
import org.example.cache.FileCacheManagerInstance;
import org.example.constpool.ConstPool;
import org.example.constpool.ModuleName;
import org.example.constpool.PluginName;
import org.example.plugin.CommonPlugin;
import org.example.plugin.annotation.Plugin;

import java.util.List;

/**
 * @author Genius
 * @date 2023/04/26 02:09
 **/

@Plugin(moduleName = ModuleName.FILE,
        pluginName = PluginName.FILE_CACHE_PLUGIN,
        pluginName_CN = "文件缓存插件",
        pluginDescription = "负责对项目文件进行缓存管理，实现高性能读取和写入，系统内置插件，不可关闭!",
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
