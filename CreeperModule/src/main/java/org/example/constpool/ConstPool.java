package org.example.constpool;

/**
 * 产量池
 * @author 燧枫
 * @date 2023/4/23 16:12
*/
public class ConstPool {

    /**
     * 文件路径
     */
    //弹幕保存文件的根目录
    public static final String BARRAGE_ROOT = (String)GlobalFileCache.ModuleSrcConfigFile.get("barrage","src");

    /**
     * 下载的方式
     */
    // 直播
    public static final String ACTION_LIVE = "live";
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
    public static final String DOUYING = "douying";

    /**
     * 其他
     */
    // 爬虫启动占位url
    public static final String OCCUURL = "https://v.douyu.com/";
}
