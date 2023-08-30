package org.example.core.component;

import org.apache.tomcat.jni.Time;
import org.example.util.TimeUtil;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 直播下载监视器
 * @author 燧枫
 * @date 2023/5/19 0:00
*/
public class StatusMonitor {

    private static final int WINDOW_SIZE = 10;  // 滑动窗口大小，单位：秒

    private long downloadedBytes = 0;
    private boolean connectionClosed = false;
    private long startTime = System.currentTimeMillis();
    private Queue<Long> recentBytes = new LinkedList<>();
    private long windowBytes = 0;  // 滑动窗口内的下载字节数


    public synchronized void addDownloadedBytes(int bytes) {
        downloadedBytes += bytes;
        windowBytes += bytes;
        recentBytes.offer((long) bytes);
        if (recentBytes.size() > WINDOW_SIZE) {
            windowBytes -= recentBytes.poll();
        }
    }


    public synchronized long getDownloadedBytes() {
        return downloadedBytes;
    }

    public synchronized double getDownloadSpeed() {  // 瞬时下载速度，单位：字节每秒
        return (double) windowBytes / WINDOW_SIZE;
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

    public void downloadLogTable(String name){
        //long start = System.currentTimeMillis();
        long start = 0;
        String log = "[%s]平均下载速度:%s B/s,瞬时下载速度:%s B/s,写入数据量:%s bytes,持续时间:%s";
        while(!isConnectionClosed()){
            String msg = String.format(log,name, getDownloadSpeedAvg(),getDownloadSpeed(),getDownloadedBytes(),
                    TimeUtil.getHMS(start));
            System.out.print("\r"+msg);
            try {
                Thread.sleep(1000);
                start++;
            } catch (InterruptedException e) {
                break;
            }
        }
        System.out.print("\r"+name+"直播已经结束，总计时间:"+TimeUtil.getHMS(start));

    }
}
