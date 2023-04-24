package org.example.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author Genius
 * @date 2023/04/24 18:47
 **/
public class TimeUtil {

    public static long getCurrentSecond(){
        return LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
    }

    public static long getSecond(LocalDateTime dateTime){
        return dateTime.toEpochSecond(ZoneOffset.of("+8"));
    }
}
