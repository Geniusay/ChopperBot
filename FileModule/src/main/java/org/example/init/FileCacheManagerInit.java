package org.example.init;

import org.example.cache.FileCacheManagerInstance;
import org.example.log.ChopperLogFactory;
import org.example.log.LoggerType;

/**
 * @author Genius
 * @date 2023/04/26 02:09
 **/
public class FileCacheManagerInit extends CommonInitMachine{

    public FileCacheManagerInit() {
        super( ChopperLogFactory.getLogger(LoggerType.File));
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
