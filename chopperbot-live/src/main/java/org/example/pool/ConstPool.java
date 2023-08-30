package org.example.pool;

import java.nio.file.Paths;

/**
 * 常量池
 * @author 燧枫
 * @date 2023/8/3 14:03
 */
public class ConstPool {

    public static String FFMEPEG_PATH = Paths.get(System.getProperty("user.dir"),"ffmpeg.exe").toString();
    public static String EDGE_DRIVER_PATH = Paths.get(System.getProperty("user.dir"),"msedgedriver.exe").toString();
}
