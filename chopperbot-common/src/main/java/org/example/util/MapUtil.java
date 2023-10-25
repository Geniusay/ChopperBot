package org.example.util;

import java.util.Map;
import java.util.Optional;

/**
 * @author Genius
 * @date 2023/10/25 18:44
 **/
public class MapUtil {
    public static String getString(Map<String,Object> map, String key){
        return Optional.ofNullable(map.get(key)).orElse("").toString();
    }
}
