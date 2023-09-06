package org.example.ws.handler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Genius
 * @date 2023/09/06 19:49
 **/
public class MessageProtocol {
    private static final String head = "type@=%s/";
    private static final String body = "data@=%s/";


    public static String getHead(String type){
         return String.format(head, type);
    }
    public static String encodeMsg(String type,String data){
        return getHead(type) + String.format(body, data);
    }

    public static String encodeMsg(String type, Map<String,String> contentBody){
        StringBuffer buffer = new StringBuffer(getHead(type));
        contentBody.forEach(
                (k,v)->{
                    buffer.append(k).append("@=").append(v).append("/");
                }
        );
        return buffer.toString();
    }

    public static Map<String,String> decodeMsg(String msg){
        String[] params = msg.split("/");
        HashMap<String, String> res = new HashMap<>();
        if(params.length>0){
            for (String param : params) {
                String[] kv = param.split("@=");
                if(kv.length==2){
                    res.put(kv[0],kv[1]);
                }
            }
        }
        return res;
    }

    public static boolean isThisType(String msg,String msgType){
        return msg.startsWith(String.format(head, msgType));
    }

}
