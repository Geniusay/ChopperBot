package org.example.core.creeper.processor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.example.bean.live.BiliBiliLive;
import org.example.bean.live.HuyaLive;
import org.example.constpool.ConstPool;
import org.example.core.processor.AbstractProcessor;
import us.codecraft.webmagic.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Genius
 * @date 2023/10/17 00:52
 **/
public class HuyaHotLiveProcessor extends AbstractProcessor {
    @Override
    public void process(Page page) {
        List<HuyaLive> liveList = new ArrayList<>();
        try {
            JSONArray Lives = JSON.parseObject(page.getRawText()).getJSONArray("vList");
            for (Object live : Lives) {
                if(live instanceof JSONObject){
                    JSONObject jsonLive = (JSONObject) live;
                    String lUid = jsonLive.getString("lUid");
                    String nickName = jsonLive.getString("sNick");
                    Integer watchNum = jsonLive.getInteger("lActivityCount");
                    String moduleId = jsonLive.getString("iGid");
                    String moduleName = jsonLive.getString("sGameFullName");
                    String introduction = jsonLive.getString("sIntroduction");
                    String roomId = jsonLive.getString("lProfileRoom");
                    String pic = jsonLive.getString("sScreenshot");
                    HuyaLive huyaLive = new HuyaLive(lUid);
                    huyaLive.setLiveName(introduction);
                    huyaLive.setPlatform(ConstPool.HUYA);
                    huyaLive.setDescription(introduction);
                    huyaLive.setLiver(nickName);
                    huyaLive.setWatcherNum(watchNum);
                    huyaLive.setModuleId(moduleId);
                    huyaLive.setModuleName(moduleName);
                    huyaLive.setLiveId(roomId);
                    huyaLive.setRoomPic(pic);
                    liveList.add(huyaLive);
                }
            }
        }catch (Exception e){
            throw e;
        }
        page.putField("data",liveList);
    }
}
