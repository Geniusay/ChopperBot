package org.example.init;

import org.example.cache.FileCacheManagerInstance;

/**
 * @author Genius
 * @date 2023/04/26 02:09
 **/
public class FileCacheManagerInit implements InitMachine{

    @Override
    public boolean init() {
        FileCacheManagerInstance.getInstance().start();
        return true;
    }
}
