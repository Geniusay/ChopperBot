package org.example.core.creeper.processor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.example.bean.live.BiliBiliLive;
import org.example.core.processor.AbstractProcessor;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;

import java.util.Map;

/**
 * @author Genius
 * @date 2023/09/10 01:13
 **/
public class BilibiliLiverCheckerProcessor extends AbstractProcessor {


    private String liver;

    public BilibiliLiverCheckerProcessor(String liver) {
        this.liver = liver;
    }

    @Override
    public void process(Page page) {
        try {
            JSONObject res = JSON.parseObject(page.getRawText()).getJSONObject("data").getJSONObject("result");
            JSONArray Lives = res.getJSONArray("live_room");
            BiliBiliLive biliBiliLive = null;
            if(Lives!=null){
                for (Object live : Lives) {
                    if(live instanceof JSONObject){
                        String uname = ((JSONObject) live).getString("uname");
                        if(uname.equals(liver)){
                            String cate_name = ((JSONObject) live).getString("cate_name");
                            int online = ((JSONObject) live).getInteger("online");
                            String roomid = ((JSONObject) live).getString("roomid");
                            String roomname = ((JSONObject) live).getString("title");
                            String liveTime = ((JSONObject) live).getString("live_time");
                            String uid = ((JSONObject) live).getString("uid");
                            String pic = ((JSONObject) live).getString("cover");
                            biliBiliLive = new BiliBiliLive(online,roomid,roomname,liver,roomname,uid,null,cate_name,null,null,null,null);
                            biliBiliLive.setShowTime(liveTime);
                            biliBiliLive.setRoomPic(pic);
                        }
                    }
                }
            }

            page.putField("data",biliBiliLive);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}
