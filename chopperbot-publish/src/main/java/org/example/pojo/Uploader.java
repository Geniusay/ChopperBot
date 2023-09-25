package org.example.pojo;

import java.util.Map;

/**
 * @author dhx
 * @date 2023/9/25 21:20
 */
public class Uploader {
    //用户Cookie
    protected String Cookie;

    //视频存放路径
    protected String videoPath;

    //请求头
    protected Map<String,String> header;

    //存放请求返回值
    protected Map<String,Object> param;

    //封面存放路径（待上传）
    protected String coverPath;

    public Uploader(String cookie, String videoPath, String coverPath) {
        Cookie = cookie;
        this.videoPath = videoPath;
        this.coverPath = coverPath;
    }

    public String getCookie() {
        return Cookie;
    }

    public void setCookie(String cookie) {
        Cookie = cookie;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public Map<String, String> getHeader() {
        return header;
    }

    public void setHeader(Map<String, String> header) {
        this.header = header;
    }

    public Map<String, Object> getParam() {
        return param;
    }

    public void setParam(Map<String, Object> param) {
        this.param = param;
    }

    public String getCoverPath() {
        return coverPath;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }
}
