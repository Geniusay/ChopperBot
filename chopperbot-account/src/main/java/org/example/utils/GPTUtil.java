package org.example.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import netscape.javascript.JSObject;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.example.core.config.GPTConfig;

import java.util.List;
import java.util.Map;

/**
 * @Date 2023/10/13
 * @Author xiaochun
 */
public class GPTUtil {

//    public static RequestBody getRequestBodyJSON(String[] labels){
//        StringBuilder label = new StringBuilder();
//        label.append("标签：[").append(labels[0]);
//        for (int i = 1;i < labels.length; i++){
//            label.append(",").append(labels[i]);
//        }
//        label.append("]");
//        String videoInfo = "弹幕：帅,这操作我要学一年,太6了";
//        String systemInfo = "请你作为一个专门看直播的观众，对下列的观众发送的弹幕内容进行分析，然后根据弹幕内容返回从以下几个标签返回给我最合适的一个标签来形容这段内容标签：[搞笑,秀操作,破防,泪目]";
//
//        Map<String, Object> message = Map.of(
//                "messages", List.of(new Role("system", "???"), new Role("user", "hello")),
//                "ai", "gpt-3.5-turbo-16k-0613",
//                "stream", false
//        );
//        String requestBodyString = JSONObject.toJSONString(message);
//        RequestBody requestBody = RequestBody.create(requestBodyString, MediaType.parse("application/json"));
//        return requestBody;
//    }

//    public static Headers getHeaders(String key){
//
//    }
//
//    @Data
//    @AllArgsConstructor
//    static class Role{
//        private String role;
//        private String content;
//    }
}
