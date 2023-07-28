package org.example.core.processor.hotmodule;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.example.bean.Live;
import org.example.bean.hotmodule.DouyuLive;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;

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

        List<Live> liveList = new ArrayList<>();
        try{
            JSONArray Lives = JSON.parseObject(page.getRawText()).getJSONObject("data").getJSONArray("rl");

            for (Object live : Lives) {
                if(live instanceof JSONObject){
                    JSONObject jsonLive = (JSONObject) live;
                    liveList.add(new DouyuLive(
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
            throw e;
        }
        page.putField("data", liveList);

    }
}
