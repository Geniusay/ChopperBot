package org.example.core.component;

import org.example.constpool.PluginName;
import org.example.core.VideoTaskMonitor;
import org.example.core.taskmonitor.CommonTaskMonitor;
import org.example.core.taskmonitor.MonitorCenter;
import org.example.plugin.PluginCheckAndDo;
import org.example.util.ThreadUtil;

import java.io.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Flv流下载器
 * @author 燧枫
 * @date 2023/5/19 0:22
*/
public class FlvHandle {

    private static final int RETRY_TIMES = 3;
    private static final int BUFFER_SIZE = 64 * 1024;  // 64 KB buffer
    private volatile boolean shouldTerminate = false;

    public void parseStream(InputStream in, String taskId, OutputStream out) {
        int retryCount = 0;
        while (retryCount < RETRY_TIMES && !shouldTerminate && !ThreadUtil.isInterrupt()) {
            try (BufferedInputStream input = new BufferedInputStream(in);
                 BufferedOutputStream output = new BufferedOutputStream(out)) {
                byte[] buffer = new byte[BUFFER_SIZE];
                int bytesRead;
                while ((bytesRead = input.read(buffer)) != -1) {
                    if (shouldTerminate) {
                        break;
                    }
                    output.write(buffer, 0, bytesRead);
                    int finalBytesRead = bytesRead;
                    PluginCheckAndDo.CheckAndDo((plugin)->{
                        VideoTaskMonitor monitor = ((MonitorCenter) plugin).getInitMonitor(taskId, VideoTaskMonitor.class);
                        if(monitor!=null){
                            monitor.addDownloadedBytes(finalBytesRead);
                        }
                    }, PluginName.TASK_MONITOR_PLUGIN);
                }
            } catch (IOException e) {
                retryCount++;
                if (retryCount < RETRY_TIMES) {
                    try {
                        Thread.sleep(1000L * retryCount);
                    } catch (InterruptedException interruptedException) {
                        Thread.currentThread().interrupt();
                    }
                } else {
                    e.printStackTrace();
                }
            }finally {
                try {
                    in.close();
                    out.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public void terminateDownload() {
        shouldTerminate = true;
    }
}


