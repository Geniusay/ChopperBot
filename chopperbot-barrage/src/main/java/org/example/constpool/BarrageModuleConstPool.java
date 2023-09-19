package org.example.constpool;

/**
 * @Description
 * @Author welsir
 * @Date 2023/8/2 23:38
 */
public class BarrageModuleConstPool {
    public static final String BARRAGE_ROOT_PATH = (String) GlobalFileCache.ModuleSrcConfigFile.get("src", ConstPool.BARRAGE);
    public static final String BARRAGE_SCORE_CONFIG = "config/barrage/barrageScoreConfig/";

    public static final String FILE_TYPE = ".json";

    public static final String GLOBAL_KEY_WORD = "global";
}
