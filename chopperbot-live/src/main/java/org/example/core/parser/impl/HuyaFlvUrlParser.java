package org.example.core.parser.impl;

import org.example.core.creeper.loadconfig.HuyaLiveOnlineConfig;
import org.example.core.parser.PlatformVideoUrlParser;
import org.example.utils.HttpClientUtil;
import org.example.utils.RegexUtil;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static cn.hutool.crypto.SecureUtil.md5;

/**
 * @author dhx
 * @date 2023/10/9 20:04
 */
public class HuyaFlvUrlParser implements PlatformVideoUrlParser<HuyaLiveOnlineConfig> {
    public static String live(String e){
        String[] parts = e.split("\\?");
        String i = parts[0];
        String b = parts[1];

        String[] r = i.split("/");
        String s = r[r.length - 1].replaceAll("\\.(flv|m3u8)", "");

        String[] c = b.split("&", 4);
        c = Arrays.stream(c).filter(item -> !item.isEmpty()).toArray(String[]::new);

        Map<String, String> n = new HashMap<>();
        for (String item : c) {
            String[] keyValue = item.split("=");
            if (keyValue.length == 2) {
                n.put(keyValue[0], keyValue[1]);
            }
        }

        String fm = java.net.URLDecoder.decode(n.get("fm"));
        byte[] fmBytes = Base64.getDecoder().decode(fm);
        String u = new String(fmBytes, java.nio.charset.StandardCharsets.UTF_8);

        String[] uParts = u.split("_");
        String p = uParts[0];

        String f = String.valueOf(System.currentTimeMillis() * 10000);
        String l = n.get("wsTime");
        String t = "0";

        String h = p + "_" + t + "_" + s + "_" + f + "_" + l;

        String m = md5(h);
        String y = c[c.length - 1];

        String url = i + "?wsSecret=" + m + "&wsTime=" + l + "&u=" + t + "&seqid=" + f + "&" + y;

        System.out.println(url); // 打印生成的URL
        return url;
    }

    @Override
    public String getUrl(HuyaLiveOnlineConfig huyaLiveOnlineConfig) throws Exception {
        String roomId = huyaLiveOnlineConfig.getRoomId();
        Map<String,String> header = new HashMap<>();
        header.put("Content-Type","application/x-www-form-urlencoded");
        header.put("User-Agent","Mozilla/5.0 (Linux; Android 5.0; SM-G900P Build/LRX21T) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Mobile Safari/537.36 ");
        try{
            String resp = HttpClientUtil.get("https://www.huya.com/"+roomId,header);
            String[] res = RegexUtil.match(resp,"\"liveLineUrl\":\"([\\s\\S]*?)\",");
            String liveLineUrl = "";
            if(res.length!=0){
                liveLineUrl = res[0];
            }
            String liveline = "";
            if(!liveLineUrl.isEmpty()){
                liveLineUrl = liveLineUrl.substring(liveLineUrl.indexOf(":")+2,liveLineUrl.indexOf(",")-1);
                liveline = new String(Base64.getDecoder().decode(liveLineUrl), StandardCharsets.UTF_8);
            }
            if(!liveline.isEmpty()){
                return liveline;
            }
            else{
                liveline = live(liveline);
                liveline = "https:" + liveline.replace("hls","flv").replace("m3u8","flv");
                return liveline;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
