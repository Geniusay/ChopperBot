package org.example.core.creeper.processor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.example.bean.HotModule;
import org.example.bean.live.DouyinLive;
import org.example.constpool.ConstPool;
import org.example.core.HotModuleDataCenter;
import org.example.core.processor.AbstractProcessor;
import org.example.util.TimeUtil;
import us.codecraft.webmagic.Page;

/**
 * @author dhx
 * @date 2024/5/26 11:40
 */
public class DouyinLiverCheckerProcessor extends AbstractProcessor {

    @Override
    public void process(Page page) {
        System.out.println(page.getHeaders());
        JSONObject live = JSON.parseObject(page.getRawText());
        DouyinLive douyinLive = null;
        if (live!=null) {
            JSONObject liveInfo = live.getJSONObject("data").getJSONArray("data").getJSONObject(0);
            JSONObject userInfo = live.getJSONObject("data").getJSONObject("user");
            String room_name = liveInfo.getString("title");
            String room_id = page.getRequest().getHeaders().get("rid");
            String pic_url = liveInfo.getJSONObject("cover").getJSONArray("url_list").getString(0);
            String owner_uid = userInfo.getString("sec_uid");
            String nickname = userInfo.getString("nickname");
            Long show_time = live.getJSONObject("extra").getLong("now");
            String description = "";
            Integer moduleId = Integer.valueOf(live.getJSONObject("data").getJSONObject("partition_road_map").getJSONObject("partition").getString("id_str"));
            String moduleName = live.getJSONObject("data").getJSONObject("partition_road_map").getJSONObject("partition").getString("title");
            try {
                HotModule module = HotModuleDataCenter.DataCenter().getModuleById(ConstPool.DOUYIN,String.valueOf(moduleId));
                moduleName = module==null?"未知模块":module.getTagName();
            }catch (Exception e){
                throw new RuntimeException(e);
            }
            douyinLive = new DouyinLive(0,room_id,room_name,nickname,description,pic_url,owner_uid,String.valueOf(moduleId),moduleName);
            douyinLive.setShowTime(TimeUtil.getFormatDate(show_time));
        }
        page.putField("data",douyinLive);
    }
}
