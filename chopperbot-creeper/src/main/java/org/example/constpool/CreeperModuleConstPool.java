package org.example.constpool;

/**
 * 产量池
 * @author 燧枫
 * @date 2023/4/23 16:12
*/
public class CreeperModuleConstPool {

    /**
     * 文件路径
     */
    //弹幕保存文件的根目录
    public static final String BARRAGE_ROOT = (String)GlobalFileCache.ModuleSrcConfigFile.get("src",ConstPool.BARRAGE);

    public static final String CREEPER_ROOT = (String)GlobalFileCache.ModuleSrcConfigFile.get("src",ConstPool.CREEPER);

    /**
     * 下载的方式
     */
    // 直播
    public static final String ACTION_LIVE = "online";
    // 录播
    public static final String ACTION_RECORD = "record";

    /**
     * 直播平台
     */
    // 斗鱼
    public static final String DOUYU = "douyu";
    // b站
    public static final String BILIBILI = "bilibili";
    // 虎牙
    public static final String HUYA = "huya";
    // 抖音
    public static final String DOUYIN = "douyin";

    /**
     * 其他
     */
    // 爬虫启动占位url
    public static final String OCCUURL = "https://v.douyu.com/";

    public static final String DOUYU_URL = "www.douyu.com";
}
