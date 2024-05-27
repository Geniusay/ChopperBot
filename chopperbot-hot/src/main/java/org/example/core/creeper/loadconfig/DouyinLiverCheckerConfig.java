package org.example.core.creeper.loadconfig;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.example.constpool.ConstGroup;
import org.example.constpool.ConstPool;
import org.example.core.creeper.loadtask.DouyinLiverCheckerLoadTask;
import org.example.core.loadconfig.LoadConfig;
import org.example.core.manager.Creeper;
import org.example.util.ByteDanceUtil;
import org.example.util.HttpClientUtil;
import org.example.util.RegexUtil;
import org.springframework.http.HttpMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dhx
 * @date 2024/5/26 10:56
 */
@Data
@Creeper(creeperName = "抖音直播检测爬虫",
        loadTask = DouyinLiverCheckerLoadTask.class,
        creeperDescription = "用于检测抖音主播是否开播，并且获取直播详细信息",
        priority = 10,
        group = ConstGroup.LIVER_CHECKER,
        platform = ConstPool.DOUYIN
)
public class DouyinLiverCheckerConfig extends LoadConfig {
    private String roomId;

    public DouyinLiverCheckerConfig(String roomId) {
        setRoomId(roomId);
        Map<String,String> dyHeader = new HashMap<>();
        dyHeader.put("Cookie",String.format("ttwid=%s;", ByteDanceUtil.getTtwid()));
        dyHeader.put("rid",roomId);
        setHeader(dyHeader);
        setUrl("https://live.douyin.com/webcast/room/web/enter/?aid=6383&app_name=douyin_web&live_id=1&device_platform=web&language=zh-CN&enter_from=web_live&cookie_enabled=true&screen_width=1728&screen_height=1117&browser_language=zh-CN&browser_platform=MacIntel&browser_name=Chrome&browser_version=116.0.0.0&web_rid="+roomId);
    }


    @Override
    public String getTaskId() {
        return super.getTaskId()+"_"+roomId;
    }
}
