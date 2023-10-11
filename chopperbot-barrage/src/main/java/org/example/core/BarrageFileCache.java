package org.example.core;

import org.example.bean.Barrage;
import org.example.cache.FileCache;
import org.example.core.creeper.file.BarrageSaveFile;
import org.example.exception.FileCacheException;

/**
 * @author Genius
 * @date 2023/10/10 18:34
 **/
public class BarrageFileCache extends FileCache<BarrageSaveFile> {

    private final long flagTime = System.currentTimeMillis();



    public BarrageFileCache(BarrageSaveFile configFile) throws FileCacheException {
        super(configFile, 0, 10 * 1024);
    }

    @Override
    public int append(Object append, String... keys) throws InterruptedException, FileCacheException {
        if(append instanceof Barrage){
            if (((Barrage) append).getTimeReal() < flagTime) {
                long current = System.currentTimeMillis();
                ((Barrage) append).setTimeReal(current);
                ((Barrage) append).setTimeIndex(current-flagTime);
            }
        }
        return super.append(append, keys);
    }
}
