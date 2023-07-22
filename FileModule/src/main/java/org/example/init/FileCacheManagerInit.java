package org.example.init;

import org.example.cache.FileCacheManagerInstance;
import org.example.log.FileModuleLogger;
import org.slf4j.Logger;

/**
 * @author Genius
 * @date 2023/04/26 02:09
 **/
public class FileCacheManagerInit extends CommonInitMachine{

    public FileCacheManagerInit() {
        super(FileModuleLogger.logger);
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
