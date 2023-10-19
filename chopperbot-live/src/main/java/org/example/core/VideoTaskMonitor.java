package org.example.core;

import org.example.core.taskmonitor.CommonTaskMonitor;
import org.example.util.ByteConvertUtil;


import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @author Genius
 * @date 2023/09/07 01:00
 **/
public class VideoTaskMonitor extends CommonTaskMonitor {

    private static final int WINDOW_SIZE = 10;  // 滑动窗口大小，单位：秒

    private long downloadedBytes = 0;

    private long startTime = System.currentTimeMillis();

    private Queue<Long> recentBytes = new LinkedList<>();

    private long windowBytes = 0;  // 滑动窗口内的下载字节数

    public VideoTaskMonitor() {
        monitorType = "live";
    }

    public void addDownloadedBytes(int bytes) {
        downloadedBytes += bytes;
        windowBytes += bytes;
        recentBytes.offer((long) bytes);
        if (recentBytes.size() > WINDOW_SIZE) {
            windowBytes -= recentBytes.poll();
        }
    }

    public long getDownloadedBytes() {
        return downloadedBytes;
    }

    public long getDownloadSpeed() {  // 瞬时下载速度，单位：字节每秒
        return  windowBytes / WINDOW_SIZE;
    }

    public long getDownloadSpeedAvg() {  // 平均下载速度，单位：字节每秒
        long elapsedSeconds = (System.currentTimeMillis() - startTime) / 1000;
        return  downloadedBytes / Math.max(1, elapsedSeconds);
    }
    @Override
    public void run() {
        if(handler!=null){
            try {
                handler.wrapperAndSend(
                        Map.of(
                                "taskId",getTaskId(),
                                "monitor",monitorType,
                                "total", ByteConvertUtil.byteConvert(getDownloadedBytes()),
                                "avg",ByteConvertUtil.byteConvert(getDownloadSpeedAvg()),
                                "now",ByteConvertUtil.byteConvert(getDownloadSpeed()),
                                "useTime",timeConsuming(),
                                "time", LocalDateTime.now()
                        ),getTaskId()
                );
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }


}
