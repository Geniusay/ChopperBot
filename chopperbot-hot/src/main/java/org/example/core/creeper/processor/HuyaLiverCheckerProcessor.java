package org.example.core.creeper.processor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.example.bean.live.HuyaLive;
import org.example.constpool.ConstPool;
import org.example.core.processor.AbstractProcessor;
import org.example.util.TimeUtil;
import us.codecraft.webmagic.Page;

import java.util.Optional;

/**
 * @author Genius
 * @date 2023/10/16 23:37
 **/
public class HuyaLiverCheckerProcessor extends AbstractProcessor {
    @Override
    public void process(Page page) {
        JSONObject live = JSON.parseObject(page.getRawText()).getJSONObject("response").getJSONObject("1");
        if(live!=null){
            JSONArray array = Optional.ofNullable(live.getJSONArray("docs")).orElse(new JSONArray());
            for (Object doc : array) {
                if(doc instanceof JSONObject){
                    if (Boolean.TRUE.equals(((JSONObject) doc).getBoolean("gameLiveOn"))) {
                        String uid = ((JSONObject) doc).getString("uid");
                        Integer activityCount = ((JSONObject) doc).getInteger("game_activityCount");
                        String avatar = ((JSONObject) doc).getString("game_avatarUrl180");
                        String moduleName = ((JSONObject) doc).getString("game_name");
                        Integer moduleId = ((JSONObject) doc).getInteger("game_id");
                        String roomId = ((JSONObject) doc).getString("room_id");
                        String liver = ((JSONObject) doc).getString("game_nick");
                        String liveName = ((JSONObject) doc).getString("live_intro");
                        Long showTime = ((JSONObject) doc).getLong("rec_live_time") * 1000;
                        HuyaLive huyaLive = new HuyaLive(uid);
                        huyaLive.setWatcherNum(activityCount);
                        huyaLive.setRoomPic(avatar);
                        huyaLive.setModuleName(moduleName);
                        huyaLive.setModuleId(moduleId.toString());
                        huyaLive.setLiveId(roomId);
                        huyaLive.setLiver(liver);
                        huyaLive.setLiveName(liveName);
                        huyaLive.setDescription(liveName);
                        huyaLive.setShowTime(TimeUtil.getFormatDate(showTime));
                        huyaLive.setPlatform(ConstPool.HUYA);
                        page.putField("data",huyaLive);
                        return;
                    }
                }
            }
        }

    }
}
