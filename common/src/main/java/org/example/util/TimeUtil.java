package org.example.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

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


    public static String getToday_YMD(){
        return LocalDate.now().toString();
    }

    public static String getYesterday_YMD(){
        return LocalDate.now().minusDays(1).toString();
    }

    public static String getNowTime_YMDHMS() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
