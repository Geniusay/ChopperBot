package org.example.video.core.handle;

import org.example.video.core.monitor.StatusMonitor;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Flv下载器
 * @author 燧枫
 * @date 2023/5/19 0:22
*/
public class FlvHandle {

    public void parseStream(InputStream in, StatusMonitor statusMonitor, OutputStream out) {
        try {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
                statusMonitor.addDownloadedBytes(bytesRead);
            }
        } catch (Exception e) {
            statusMonitor.setConnectionClosed(true);
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

