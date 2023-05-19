package org.example.video;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import org.example.video.core.handle.FlvHandle;
import org.example.video.core.monitor.StatusMonitor;
import org.example.video.core.parser.BilibiliFlvUrlParse;

/**
 *
 * @author 燧枫
 * @date 2023/5/18 22:23
*/
public class BilibiliLiveStreamTest {

    static String videoPath = "F:\\";

    public void startFlvStreamParse(String url, StatusMonitor statusMonitor, OutputStream fileIO, Map<String, String> headers) {
        new Thread(() -> {
            FlvHandle f = new FlvHandle();
            try {
                URLConnection conn = new URL(url).openConnection();
                if (headers != null) {
                    for (Map.Entry<String, String> entry : headers.entrySet()) {
                        conn.setRequestProperty(entry.getKey(), entry.getValue());
                    }
                }
                InputStream in = conn.getInputStream();
                f.parseStream(in, statusMonitor, fileIO);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void startFlvStreamParse(String url, StatusMonitor statusMonitor, OutputStream fileIO) {
        Map<String, String> defaultHeaders = new HashMap<>();
        defaultHeaders.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36");
        defaultHeaders.put("Origin", "https://live.bilibili.com");
        defaultHeaders.put("Referer", "https://live.bilibili.com/");

        startFlvStreamParse(url, statusMonitor, fileIO, defaultHeaders);
    }



    public static void main(String[] args) {
        String roomId = "732";
        int qn = 10000;

        StatusMonitor statusMonitor = new StatusMonitor();
        BilibiliLiveStreamTest bilibiliLiveStreamTest = new BilibiliLiveStreamTest();

        BilibiliFlvUrlParse bilibiliFlvUrlParse = new BilibiliFlvUrlParse();

        try {
            String url = bilibiliFlvUrlParse.getFlvUrl(roomId, qn);
            System.out.println(url);

            OutputStream fileIO = new FileOutputStream(videoPath + roomId + ".flv");

            bilibiliLiveStreamTest.startFlvStreamParse(url, statusMonitor, fileIO);

            while (true) {
                // 注意我们在这里没有包含 isStopFlag 和 getVideoTagSpeed 方法，
                // 因为这些方法的实现可能需要更复杂的逻辑
                if (statusMonitor.isConnectionClosed()) {
                    System.out.println("连接中断，已停止录制...");
                    break;
                }
                // 输出实时的下载状态
                System.out.println("平均下载速度：" + statusMonitor.getDownloadSpeedAvg() + " B/s");
                System.out.println("瞬时下载速度：" + statusMonitor.getDownloadSpeed() + " B/s");
                System.out.println("已写入数据量：" + statusMonitor.getDownloadedBytes() + " bytes");
                Thread.sleep(1000);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
