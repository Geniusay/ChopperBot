package org.example.core.component;

import org.example.log.ChopperLogFactory;
import org.example.log.LoggerType;
import org.example.pool.LiveModuleConstPool;
import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;

/**
 * M3U8流处理器
 * @author 燧枫
 * @date 2023/8/3 12:47
 */
public class M3U8Handle {

    // 指定开始时间与结束时间下载某个m3u8视频
    public static void downloadAndCutVideo(String url, String startTime, String endTime, String downloadLocation, String outputFileName) {
        String outputFilePath = Paths.get(downloadLocation, outputFileName).toString();
        handleProcess(url, startTime, endTime, outputFilePath);
    }
    public static void downloadAndCutVideo(String url, long startTime, long endTime, String downloadLocation, String outputFileName) {
        String outputFilePath = Paths.get(downloadLocation, outputFileName).toString();
        handleProcess(url, String.valueOf(startTime), String.valueOf(endTime), outputFilePath);
    }
    private static void handleProcess(String url, String startTime, String endTime, String outputFilePath) {
        Logger logger = ChopperLogFactory.getLogger(LoggerType.LiveRecord);
        logger.info("Starting video download and processing...");
        ProcessBuilder processBuilder = new ProcessBuilder(
                LiveModuleConstPool.FFMEPEG_PATH,
                "-i", url,
                "-ss", startTime,
                "-to", endTime,
                "-c", "copy",
                "-y",
                outputFilePath
        );
        processBuilder.redirectErrorStream(true);
        try {
            Process process = processBuilder.start();
            try (BufferedReader inputReader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = inputReader.readLine()) != null) {
                    System.out.println("FFmpeg output: " + line);
                }
            }
            int exitCode = process.waitFor();
            logger.error("Exit code: {}",exitCode);
        } catch (IOException | InterruptedException e) {
            logger.error("Error during video processing: {}",e.getMessage());
            e.printStackTrace();
        }
    }
}
