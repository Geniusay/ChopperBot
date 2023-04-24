package org.example.cache;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import org.example.bean.ModuleSrcConfigFile;
import org.example.bean.Student;
import org.example.cache.FileCache;
import org.example.exception.FileCacheException;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Genius
 * @date 2023/04/24 03:30
 **/

public class FileCacheTest {

    @Test
    public void TestFileCache() throws FileCacheException, InterruptedException {
        ModuleSrcConfigFile moduleSrcConfigFile = new ModuleSrcConfigFile();
        FileCache fileCache = new FileCache(moduleSrcConfigFile);

        FileCacheManager manager = new FileCacheManager(List.of(fileCache));
        manager.start();
        Thread.sleep(500000);
    }

    @Test
    public void TestFileCacheManager() throws FileCacheException, InterruptedException {
        ModuleSrcConfigFile moduleSrcConfigFile = new ModuleSrcConfigFile();
        FileCache fileCache = new FileCache(moduleSrcConfigFile);

        FileCacheManager manager = new FileCacheManager(List.of(fileCache));
        manager.start();
        for(int i=0;i<1000;i++){
            fileCache.append("sectionwork",Integer.toString(i));
        }
        Thread.sleep(500000);
    }

}
