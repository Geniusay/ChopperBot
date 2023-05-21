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

    public void parseStream(InputStream in, StatusMonitor statusMonitor, OutputStream out) {
        int retryCount = 0;
        while (retryCount < RETRY_TIMES) {
            try (BufferedInputStream input = new BufferedInputStream(in);
                 BufferedOutputStream output = new BufferedOutputStream(out)) {
                byte[] buffer = new byte[BUFFER_SIZE];
                int bytesRead;
                while ((bytesRead = input.read(buffer)) != -1) {
                    output.write(buffer, 0, bytesRead);
                    statusMonitor.addDownloadedBytes(bytesRead);
                }
                break;
            } catch (IOException e) {
                retryCount++;
                if (retryCount < RETRY_TIMES) {
                    try {
                        Thread.sleep(1000L * retryCount);  // Exponential backoff
                    } catch (InterruptedException interruptedException) {
                        Thread.currentThread().interrupt();
                    }
                } else {
                    statusMonitor.setConnectionClosed(true);
                    e.printStackTrace();
                }
            }
        }
    }
}


