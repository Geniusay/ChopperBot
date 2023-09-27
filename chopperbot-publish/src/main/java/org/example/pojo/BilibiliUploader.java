package org.example.pojo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dhx
 * @date 2023/9/25 21:27
 */
public class BilibiliUploader extends Uploader{
    private Map<String,String> header2;

    private String deviceVideoPath;

    public BilibiliUploader(String cookie, String videoPath, String coverPath, String deviceVideoPath) {
        super(cookie, videoPath, coverPath);
        this.deviceVideoPath = deviceVideoPath;
        Map<String,String> header = new HashMap<>();
        header.put("Cookie", cookie);
        header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36 Edg/116.0.1938.69");
        header.put("Referer", "https://member.bilibili.com/platform/upload/video/frame?spm_id_from=333.1007.top_bar.upload");
        this.header = header;
    }

    public Map<String, String> getHeader2() {
        return header2;
    }

    public void setHeader2(Map<String, String> header2) {
        this.header2 = header2;
    }

    public String getDeviceVideoPath() {
        return deviceVideoPath;
    }

    public void setDeviceVideoPath(String deviceVideoPath) {
        this.deviceVideoPath = deviceVideoPath;
    }

}
