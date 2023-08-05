package org.example.core.component;

import java.io.*;

/**
 * Flv流下载器
 * @author 燧枫
 * @date 2023/5/19 0:22
*/
public class FlvHandle {

    private static final int RETRY_TIMES = 3;
    private static final int BUFFER_SIZE = 64 * 1024;  // 64 KB buffer
    private volatile boolean shouldTerminate = false;

    public void parseStream(InputStream in, StatusMonitor statusMonitor, OutputStream out) {
        int retryCount = 0;
        while (retryCount < RETRY_TIMES && !shouldTerminate) {
            try (BufferedInputStream input = new BufferedInputStream(in);
                 BufferedOutputStream output = new BufferedOutputStream(out)) {
                byte[] buffer = new byte[BUFFER_SIZE];
                int bytesRead;
                while ((bytesRead = input.read(buffer)) != -1) {
                    if (shouldTerminate) {
                        break;
                    }
                    output.write(buffer, 0, bytesRead);
                    statusMonitor.addDownloadedBytes(bytesRead);
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
                    statusMonitor.setConnectionClosed(true);
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


