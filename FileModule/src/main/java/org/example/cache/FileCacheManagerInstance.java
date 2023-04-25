package org.example.cache;

import org.example.constpool.GlobalFileCache;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Genius
 * @date 2023/04/25 22:14
 **/

//FileCacheManager单例实体类
public class FileCacheManagerInstance {

    //获取全局的一个fileCaches
    private static List<FileCache> fileCaches = GlobalFileCache.fileCaches;
    private static volatile FileCacheManager Instance;

    public static FileCacheManager getInstance(){
        if(Instance==null){
            synchronized (FileCacheManagerInstance.class){
                if(Instance==null){
                    Instance = new FileCacheManager(fileCaches);
                }
            }
        }
        return Instance;
    }
}
