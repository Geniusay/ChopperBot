package org.example.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.regex.Pattern;

/**
 * @author dhx
 * @date 2023/9/18 15:30
 */
public class FileUtil {
    /**
     * 获取文件大小
     * @param filePath 文件路径
     * @return 文件大小
     */
    public static int getFilesize(String filePath){
        int filesize = 0;
        FileInputStream fileInputStream = null;
        try {
            File file = new File(filePath);
            if (file.exists() && file.isFile()) {
                fileInputStream = new FileInputStream(file);
                filesize = fileInputStream.available();
                fileInputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return filesize;
    }


}
