package org.example.constpool;

import org.example.cache.FileCache;

/**
 * @author Genius
 * @date 2023/04/25 23:03
 **/

/**
 * 全局文件缓存池，用于存放全局文件缓存，便于跨模块调用
 */
public class GlobalFileCache {

    public GlobalFileCache() {
    }

    public static FileCache ModuleSrcConfigFile;


}
