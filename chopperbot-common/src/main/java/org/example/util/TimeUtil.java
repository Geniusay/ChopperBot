package org.example.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author Genius
 * @date 2023/04/24 18:47
 **/
public class TimeUtil {

    /**
     * 获得当前的总秒数
     * @return
     */
    public static long getCurrentSecond(){
        return LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
    }

    /**
     * 获得指定时间的总秒数
     * @param dateTime
     * @return
     */
    public static long getSecond(LocalDateTime dateTime){
        return dateTime.toEpochSecond(ZoneOffset.of("+8"));
    }


    /**
     * 获得YMD格式的今日时间，如：2023-6-10
     * @return
     */
    public static String getToday_YMD(){
        return LocalDate.now().toString();
    }

    /**
     * 获得YMD格式的昨日时间，如：2023-6-10
     * @return
     */
    public static String getYesterday_YMD(){
        return LocalDate.now().minusDays(1).toString();
    }

    /**
     * 获得当前时间的YMDHMS格式的今日时间,如: 2023-6-10 10:23:04
     * @return
     */
    public static String getNowTime_YMDHMS() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public static long getTimeNaos(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            return sdf.parse(date).getTime();
        } catch (ParseException e) {
            return 0;
        }
    }

    public static String getHMS(Long seconds){
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long remainingSeconds = seconds % 60;

        return hours + "h " + minutes + "m " + remainingSeconds + "s";
    }

    public static String getFormatDate(Long timestamp){
        Date date = new Date(timestamp);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
}
