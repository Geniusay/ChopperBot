package org.example.core.processor.hotmodule;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.example.bean.HotLive;
import org.example.bean.hotmodule.DouyuHotLive;
import org.example.constpool.HotModulePool;
import org.example.log.HotModuleLogger;
import org.example.util.ChineseConvertUtil;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Json;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Genius
 * @date 2023/07/19 00:30
 **/
public class DouyuHotLiveProcessor implements PageProcessor {


    @Override
    public void process(Page page) {

        List<HotLive> hotLiveList = new ArrayList<>();
        try{
            JSONArray Lives = JSON.parseObject(page.getRawText()).getJSONArray("data");
            for (Object live : Lives) {
                if(live instanceof JSONObject){
                    JSONObject jsonLive = (JSONObject) live;
                    hotLiveList.add(new DouyuHotLive(
                            ChineseConvertUtil.cnNumericUnitsToInt(jsonLive.getString("hot")),
                            Integer.parseInt(jsonLive.getString("relId")),
                            jsonLive.getString("sk"),
                            jsonLive.containsKey("description")?jsonLive.getString("description"):"",
                            jsonLive.getInteger("hotTag"),
                            jsonLive.getInteger("isAd"),
                            jsonLive.getInteger("type"),
                            jsonLive.getString("url")
                    ));
                }
            }
        }catch (Exception e){
            HotModuleLogger.logger.error("DouYu Hot Live List require fail! Exception:{}",e.getMessage());
        }
        HotModulePool.hotLiveListPool.put(HotModulePool.DouYuAllHotLives,hotLiveList);

    }
}
