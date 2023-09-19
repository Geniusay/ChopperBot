package org.example.core.creeper.processor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.example.bean.HotModule;
import org.example.bean.live.BiliBiliLive;
import org.example.bean.live.DouyuLive;
import org.example.constpool.ConstPool;
import org.example.core.HotModuleDataCenter;
import org.example.core.processor.AbstractProcessor;
import org.example.util.TimeUtil;
import us.codecraft.webmagic.Page;

/**
 * @author Genius
 * @date 2023/09/10 01:58
 **/
public class DouyuLiverCheckerProcessor extends AbstractProcessor {

    @Override
    public void process(Page page) {
        JSONObject live = JSON.parseObject(page.getRawText()).getJSONObject("room");
        DouyuLive douyuLive = null;
        if (live.getInteger("show_status")==1) {
            String room_name = live.getString("room_name");
            String room_id = live.getString("room_id");
            String pic_url = live.getJSONObject("avatar").getString("big");
            String owner_uid = live.getString("owner_uid");
            String nickname = live.getString("nickname");
            Long show_time = (live.getLong("show_time") * 1000);
            String show_details = live.getString("show_details");
            Integer moduleId = live.getInteger("cate_id");
            String moduleName = "未知模块";
            try {
                HotModule module = HotModuleDataCenter.DataCenter().getModule(ConstPool.DOUYU,moduleId);
                moduleName = module==null?"未知模块":module.getTagName();
            }catch (Exception e){
                throw new RuntimeException(e);
            }
            douyuLive = new DouyuLive(0,room_id,room_name,nickname,show_details,pic_url,owner_uid,String.valueOf(moduleId),moduleName);
            douyuLive.setShowTime(TimeUtil.getFormatDate(show_time));
        }
        page.putField("data",douyuLive);
    }
}
