package org.example.init;

import org.example.cache.FileCacheManagerInstance;
import org.example.constpool.PluginName;
import org.example.log.ChopperLogFactory;
import org.example.log.LoggerType;

/**
 * @author Genius
 * @date 2023/04/26 02:09
 **/
public class FileCacheManagerInitMachine extends CommonInitMachine{

    public FileCacheManagerInitMachine() {
        super(ChopperLogFactory.getLogger(LoggerType.File), PluginName.FILE_CACHE_PLUGIN);
    }

    @Override
    public boolean init() {
        try {
            FileCacheManagerInstance.getInstance().start();
            return success();
        }catch (Exception e){
            return fail(e.getMessage());
        }
    }

    @Override
    public void shutdown() {
        super.shutdown();
        FileCacheManagerInstance.getInstance().close();
    }
}
