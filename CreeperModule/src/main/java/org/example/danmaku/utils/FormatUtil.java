package org.example.danmaku.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 格式工具类(日期,实体戳等等)
 * @author 燧枫
 * @date 2023/4/23 17:50
*/
public class FormatUtil {

    // 日期格式
    public static SimpleDateFormat formatter= new SimpleDateFormat("MM-dd-HH_mm_ss");

    // 获取当前日期
    public static String getNowDate() {
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }

}
