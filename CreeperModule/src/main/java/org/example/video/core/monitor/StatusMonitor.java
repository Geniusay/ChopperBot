package org.example.video.core.monitor;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author 燧枫
 * @date 2023/5/19 0:00
*/
public class StatusMonitor {
    private long downloadedBytes = 0;
    private boolean connectionClosed = false;
    private long startTime = System.currentTimeMillis();
    private Queue<Long> recentBytes = new LinkedList<>();
    private static final int QUEUE_SIZE = 10;  // 储存最近10秒的下载字节数

    public synchronized void addDownloadedBytes(int bytes) {
        downloadedBytes += bytes;
        recentBytes.offer((long) bytes);
        if (recentBytes.size() > QUEUE_SIZE) {
            recentBytes.poll();
        }
    }

    public synchronized long getDownloadedBytes() {
        return downloadedBytes;
    }

    public synchronized double getDownloadSpeed() {  // 瞬时下载速度，单位：字节每秒
        return recentBytes.stream().mapToLong(Long::longValue).sum();
    }

    public synchronized double getDownloadSpeedAvg() {  // 平均下载速度，单位：字节每秒
        long elapsedSeconds = (System.currentTimeMillis() - startTime) / 1000;
        return (double) downloadedBytes / Math.max(1, elapsedSeconds);
    }

    public synchronized void setConnectionClosed(boolean connectionClosed) {
        this.connectionClosed = connectionClosed;
    }

    public synchronized boolean isConnectionClosed() {
        return connectionClosed;
    }
}
