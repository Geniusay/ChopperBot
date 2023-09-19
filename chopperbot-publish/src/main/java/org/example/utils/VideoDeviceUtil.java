package org.example.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 视频分段工具类
 * @author dhx
 * @date 2023/9/18 15:08
 */
public class VideoDeviceUtil {
    /**
     * videoPath: 视频存放路径
     * savePath: 分割后视频存放路径
     * chunk_size: 每一段大小
     * 返回值: 分为多少块
     */
    public static int device(String videoPath,String savePath,Long chunk_size){
        int chunkNumber = 1;
        try {
            File inputFile = new File(videoPath);
            FileInputStream fileInputStream = new FileInputStream(inputFile);
            byte[] buffer = new byte[Math.toIntExact(chunk_size)];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                String outputFilePath = savePath + "chunk_" + chunkNumber + ".bin";
                File outputFile = new File(outputFilePath);
                FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
                fileOutputStream.write(buffer, 0, bytesRead);
                fileOutputStream.close();
                chunkNumber++;
            }
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
        return chunkNumber;
    }
}
