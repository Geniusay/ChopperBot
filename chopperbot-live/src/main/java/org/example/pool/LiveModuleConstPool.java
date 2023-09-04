package org.example.pool;

import org.example.constpool.ConstPool;
import org.example.constpool.GlobalFileCache;

import java.nio.file.Paths;

/**
 * 常量池
 * @author 燧枫
 * @date 2023/8/3 14:03
 */
public class LiveModuleConstPool {

    public static String FFMEPEG_PATH = Paths.get(System.getProperty("user.dir"),"ffmpeg.exe").toString();
    public static String EDGE_DRIVER_PATH = Paths.get(System.getProperty("user.dir"),"msedgedriver.exe").toString();

    public static final String LIVE_MODULE_CONFIG_ROOT = (String) GlobalFileCache.ModuleSrcConfigFile.get("src", ConstPool.LIVE_RECORD);
    //获取平台直播视频存储位置
    public static final String getPlatformLiveSavePath(ConstPool.PLATFORM platform){
        return Paths.get(LIVE_MODULE_CONFIG_ROOT,"online",platform.getName()).toString();
    }

    public static final String getPlatformRecordSavePath(ConstPool.PLATFORM platform){
        return Paths.get(LIVE_MODULE_CONFIG_ROOT,"record",platform.getName()).toString();
    }
}
