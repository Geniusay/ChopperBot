package org.example.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 爬虫配置文件工具类
 * @author 燧枫
 * @date 2023/4/24 11:02
*/
public class PachongConfig {

    private static final Properties properties;

    static {
        properties = new Properties();
        String configFileName = "pachong.properties";
        try (InputStream inputStream = PachongConfig.class.getClassLoader().getResourceAsStream(configFileName)) {
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new RuntimeException("Configuration file not found: " + configFileName);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading configuration file: " + configFileName, e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static int getIntProperty(String key) {
        return Integer.parseInt(properties.getProperty(key));
    }
}
