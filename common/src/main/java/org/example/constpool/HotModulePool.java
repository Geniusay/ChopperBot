package org.example.constpool;

import org.example.bean.HotLive;
import org.example.bean.hotmodule.HotModuleList;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Genius
 * @date 2023/07/18 22:16
 **/
public class HotModulePool {

    static {
        hotModuleListPool = new ConcurrentHashMap<>();
        hotLiveListPool = new ConcurrentHashMap<>();
    }

    public static final String DouYuAllHotModules = "DouyuAllHotModules";

    public static final String DouYuAllHotLives = "DouyuAllHotLives";
    public static ConcurrentHashMap<String, HotModuleList> hotModuleListPool;

    public static ConcurrentHashMap<String, List<HotLive>> hotLiveListPool;
}
