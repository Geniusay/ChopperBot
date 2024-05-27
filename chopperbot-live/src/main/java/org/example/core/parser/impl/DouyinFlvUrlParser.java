package org.example.core.parser.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.example.core.creeper.loadconfig.DouyinLiveOnlineConfig;
import org.example.core.parser.PlatformVideoUrlParser;
import org.example.util.HttpClientUtil;
import org.example.util.ByteDanceUtil;

import java.util.Map;

/**
 * @author dhx
 * @date 2024/5/19 15:32
 */
public class DouyinFlvUrlParser implements PlatformVideoUrlParser<DouyinLiveOnlineConfig> {

    @Override
    public String getUrl(DouyinLiveOnlineConfig loadConfig) throws Exception {
        String url = loadConfig.getUrl();
        String roomId = loadConfig.getRoomId();
        Map<String,String> header = loadConfig.getHeader();
        try {
            header.put("Cookie",String.format("ttwid=%s;",ByteDanceUtil.getTtwid()));
            String resp = HttpClientUtil.get(url+roomId,header);
            JSONObject jsonObject = JSON.parseObject(resp);
            if(jsonObject==null||jsonObject.getJSONObject("data")==null)return null;
            return (String) jsonObject.getJSONObject("data").getJSONArray("data").getJSONObject(0).getJSONObject("stream_url").getJSONObject("flv_pull_url").get("FULL_HD1");
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
