package org.example.util;
/**
 * @description : [fileUtils]
 * @author : [Welsir]
 * @createTime : [2023/5/18 18:14]
 */

import org.example.config.BarrageScoreConfig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author welsir
 * @date 2023/5/18 18:14
 */
public class FileUtil {

    public static String JSONFileToString(String filePath){
        String fileString = "";
        try (InputStream inputStream = FileUtil.class.getResourceAsStream(filePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileString += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileString;
    }
}
