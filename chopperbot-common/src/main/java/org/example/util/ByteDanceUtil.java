package org.example.util;

import org.springframework.http.HttpMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dhx
 * @date 2024/5/26 13:16
 */
public class ByteDanceUtil {
    public static String getTtwid( ) {
        Map<String,String> douyinHeader = new HashMap<>();
        douyinHeader.put("UserAgent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36");
        douyinHeader.put("Origin","https://live.douyin.com");
        douyinHeader.put("Referer","https://live.douyin.com");
        douyinHeader.put("Upgrade-Insecure-Requests","1");
        douyinHeader.put("Accept","*/*");
        douyinHeader.put("Host","live.douyin.com");
        douyinHeader.put("Connection","keep-alive");
        Map<String,String> respHeaders = HttpClientUtil.getResponseHeaders("https://live.douyin.com/", HttpMethod.GET,"",douyinHeader);
        String ttwid = RegexUtil.match(respHeaders.get("Set-Cookie"),"ttwid=([^;]+)")[1];
        if(ttwid!=null){
            return ttwid;
        }
        String ac_nonce = RegexUtil.match(respHeaders.get("Set-Cookie"),"__ac_nonce=([a-zA-Z0-9]+)")[1];
        douyinHeader.put("Cookie",String.format("__ac_nonce=%s",ac_nonce));
        respHeaders = HttpClientUtil.getResponseHeaders("https://live.douyin.com/", HttpMethod.GET,"",douyinHeader);
        return RegexUtil.match(respHeaders.get("Set-Cookie"),"ttwid=([^;]+)")[0];
    }
}
