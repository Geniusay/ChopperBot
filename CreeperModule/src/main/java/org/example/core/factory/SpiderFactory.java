package org.example.core.factory;

import com.alibaba.fastjson.JSONObject;
import org.example.cache.FileCache;
import org.example.cache.FileCacheManagerInstance;
import org.example.config.CreeperConfigFile;
import org.example.config.SpiderConfig;
import org.example.constpool.ConstPool;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Genius
 * @date 2023/08/06 22:07
 **/
public class SpiderFactory {

    private static Map<String, SpiderConfig> spiderConfigMap;

    static {
        spiderConfigMap = new HashMap<>();
        FileCache cache = FileCacheManagerInstance.getInstance().getFileCache(CreeperConfigFile.getFullFilePath());
        JSONObject creeperConfig = (JSONObject) cache.get("spiderConfig");
        for (ConstPool.PLATFORM value : ConstPool.PLATFORM.values()) {
            String platform = value.getName();
            if (creeperConfig.containsKey(platform)) {
                spiderConfigMap.put(platform,creeperConfig.getObject(platform, SpiderConfig.class));
            }
        }
    }

    public static Spider buildSpider(PageProcessor processor,String url){
        return Spider.create(processor).addUrl(url);
    }
    public static Spider buildSpider(String platform, PageProcessor processor,String url){
        SpiderConfig spiderConfig = spiderConfigMap.get(platform);
        if(spiderConfig==null){
            return Spider.create(processor).addUrl(url);
        }

        processor.getSite()
                .setUserAgent(spiderConfig.getUserAgent())
                .setRetrySleepTime(spiderConfig.getRetrySleepTime())
                .setRetryTimes(spiderConfig.getRetryTimes())
                .setRetrySleepTime(spiderConfig.getRetrySleepTime());

        return Spider
                .create(processor)
                .thread(spiderConfig.getThreadCnt())
                .addUrl(url);
    }

}
