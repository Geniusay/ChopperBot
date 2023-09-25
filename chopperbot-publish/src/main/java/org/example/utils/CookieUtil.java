package org.example.utils;

import org.example.exception.ChopperBotException;

/**
 * @author dhx
 * @date 2023/9/24 21:50
 */
public class CookieUtil {
    public static String getParam(String Cookie,String paramName){
        try {
            String substring = Cookie.substring(Cookie.indexOf(paramName));
            String val;
            val = substring.substring(substring.indexOf("=") + 1, substring.indexOf(";"));
            return val;
        } catch (Exception e) {
            return null;
        }
    }
}
