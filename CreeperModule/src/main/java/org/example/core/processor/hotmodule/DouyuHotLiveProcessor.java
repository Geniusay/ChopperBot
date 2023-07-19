package org.example.core.processor.hotmodule;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.example.bean.HotLive;
import org.example.bean.HotModule;
import org.example.bean.hotmodule.DouyuHotLive;
import org.example.bean.hotmodule.HotModuleList;
import org.example.constpool.HotModulePool;
import org.example.log.HotModuleLogger;
import org.example.util.ChineseConvertUtil;
import org.springframework.util.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Json;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Genius
 * @date 2023/07/19 00:30
 **/
public class DouyuHotLiveProcessor implements PageProcessor {
    private int moduleId;         // 模块Id

    public DouyuHotLiveProcessor(){
        this.moduleId = -1;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    @Override
    public void process(Page page) {

        List<HotLive> hotLiveList = new ArrayList<>();
        try{
            JSONArray Lives = JSON.parseObject(page.getRawText()).getJSONObject("data").getJSONArray("rl");

            for (Object live : Lives) {
                if(live instanceof JSONObject){
                    JSONObject jsonLive = (JSONObject) live;
                    hotLiveList.add(new DouyuHotLive(
                            jsonLive.getInteger("ol"),
                            jsonLive.getInteger("rid"),
                            jsonLive.getString("rn"),
                            jsonLive.getString("nn"),
                            jsonLive.getString("od"),
                            jsonLive.getString("c2name"),
                            jsonLive.getString("url"),
                            jsonLive.getString("rs16"),
                            jsonLive.getInteger("type"),
                            jsonLive.getInteger("uid"),
                            jsonLive.getInteger("cid2")
                    ));
                }
            }
        }catch (Exception e){
            HotModuleLogger.logger.error("Douyu Hot Live List require fail! Exception:{}",e.getMessage());
        }
        HotModuleLogger.logger.info(hotLiveList.toString());
        updateHotLiveList(hotLiveList);

    }
    private void updateHotLiveList(List<HotLive> hotLiveList){
        if(moduleId!=-1){
            if(HotModulePool.hotModuleListPool.containsKey(HotModulePool.DouYuAllHotModules)){
                HotModuleList hotModuleList = HotModulePool.hotModuleListPool.get(HotModulePool.DouYuAllHotModules);
                HotModule hotModule = hotModuleList.findHotModule(this.moduleId);
                if(hotModule!=null){
                    hotModule.setHotLives(hotLiveList);
                    HotModuleLogger.logger.info("Douyu module {} hotLiveListPool successfully updated",hotModule.getTagName());
                }
            }
        }else{
            HotModuleLogger.logger.info("Douyu hotLiveListPool successfully updated");
            HotModulePool.hotLiveListPool.put(HotModulePool.DouYuAllHotLives,hotLiveList);
        }
    }
}
