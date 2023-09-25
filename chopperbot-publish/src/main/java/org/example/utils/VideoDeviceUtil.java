package org.example.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * 视频分段工具类
 * @author dhx
 * @date 2023/9/18 15:08
 */
public class VideoDeviceUtil {
    /**
     * @param videoPath: 视频存放路径
     * @param savePath: 分割后视频存放路径
     * @param chunk_size: 每一段大小
     * @return 分为多少块
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

    /**
     * 删除分段后的视频
     * @param directoryPath
     */
    public static void deleteChunks(String directoryPath){
        File directory = new File(directoryPath);
        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("目录不存在或不是一个目录。");
            return;
        }
        try{
            Pattern pattern = Pattern.compile("chunk_\\d+\\.bin");

            File[] files = directory.listFiles((dir, name) -> pattern.matcher(name).matches());

            if (files != null) {
                for (File file : files) {
                    if (file.delete()) {
                        System.out.println("已删除文件: " + file.getName());
                    } else {
                        System.out.println("无法删除文件: " + file.getName());
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
