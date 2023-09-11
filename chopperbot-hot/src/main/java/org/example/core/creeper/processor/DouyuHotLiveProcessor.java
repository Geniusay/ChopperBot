package org.example.core.creeper.processor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.example.bean.Live;
import org.example.bean.live.DouyuLive;
import org.example.core.processor.AbstractProcessor;
import us.codecraft.webmagic.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Genius
 * @date 2023/07/19 00:30
 **/
public class DouyuHotLiveProcessor extends AbstractProcessor {

    public DouyuHotLiveProcessor(){

    }

    @Override
    public void process(Page page) {

        List<DouyuLive> liveList = new ArrayList<>();
        try{
            JSONArray Lives = JSON.parseObject(page.getRawText()).getJSONObject("data").getJSONArray("rl");

            for (Object live : Lives) {
                if(live instanceof JSONObject){
                    JSONObject jsonLive = (JSONObject) live;
                    DouyuLive douyuLive = new DouyuLive(
                            jsonLive.getInteger("ol"),
                            jsonLive.getString("rid"),
                            jsonLive.getString("rn"),
                            jsonLive.getString("nn"),
                            jsonLive.getString("od"),
                            jsonLive.getString("rs16"),
                            jsonLive.getString("uid"),
                            jsonLive.getString("cid2"),
                            jsonLive.getString("c2name")
                    );
                    douyuLive.setRoomPic(jsonLive.getJSONArray("rs_ext").getJSONObject(1).getString("rs16"));
                    liveList.add(douyuLive);
                }
            }
        }catch (Exception e){
            throw e;
        }
        page.putField("data", liveList);

    }
}
