package org.example.core.creeper.processor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.example.bean.Live;
import org.example.bean.live.BiliBiliLive;
import org.example.bean.live.DouyuLive;
import org.example.core.processor.AbstractProcessor;
import us.codecraft.webmagic.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Genius
 * @date 2023/09/03 16:03
 **/
public class BiliBiliHotLiveProcessor extends AbstractProcessor {


    @Override
    public void process(Page page) {
        List<BiliBiliLive> liveList = new ArrayList<>();
        try {
            JSONArray Lives = JSON.parseObject(page.getRawText()).getJSONObject("data").getJSONArray("list");
            for (Object live : Lives) {
                if(live instanceof JSONObject){
                    JSONObject jsonLive = (JSONObject) live;
                    liveList.add(new BiliBiliLive(
                            jsonLive.getInteger("online"),
                            jsonLive.getString("roomid"),
                            jsonLive.getString("title"),
                            jsonLive.getString("uname"),
                            jsonLive.getString("title"),
                            jsonLive.getString("uid"),
                            jsonLive.getString("area_id"),
                            jsonLive.getString("area_name"),
                            jsonLive.getString("parent_id"),
                            jsonLive.getString("parent_name"),
                            jsonLive.getString("session_id"),
                            jsonLive.getString("group_id")
                    ));
                }
            }
        }catch (Exception e){
            throw e;
        }
        page.putField("data",liveList);
    }
}
