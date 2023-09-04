package org.example.util;

/**
 * @author Genius
 * @date 2023/07/19 02:17
 **/

/**
 * 中文转化工具
 */
public class ChineseConvertUtil {

    /**
     * 中文数量单位转Int
     * @param str 中文字符串 如：“350.1万”
     * @return
     */
    public static int cnNumericUnitsToInt(String str){
        String[] parts = str.split("(?<=\\d\\.\\d)(?=\\D)");
        if(parts.length==2){
            float num = Float.parseFloat(parts[0]);
            switch (parts[1]){
                case "亿":
                    return (int)num*100000;
                case "万":
                    return (int)num*10000;
                case "千":
                    return (int)num*1000;
                case "百":
                    return (int)num*100;
                case "个":
                    return (int)num;
            }
        }
        return Integer.parseInt(str);
    }

}
