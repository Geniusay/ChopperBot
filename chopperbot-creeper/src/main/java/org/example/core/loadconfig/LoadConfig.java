package org.example.core.loadconfig;

import org.example.util.TimeUtil;
import org.example.utils.FormatUtil;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;

/**
 * LoadTask配置类抽象类，作为任务配置类的最底层
 * @author Genius
 * @date 2023/07/26 15:25
 **/

public abstract class LoadConfig implements Serializable {

    protected String url;

    protected String startTime;

    protected String UserAgent;

    protected String Origin;

    protected String Referer;

    protected Map<String,String> header;

    protected Map<String,String> cookie;

    public LoadConfig() {
        this.startTime = TimeUtil.getNowTime_YMDHMS();
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTaskId(){
        return UUID.randomUUID().toString();
    }

    public String getUserAgent() {
        return UserAgent;
    }

    public void setUserAgent(String userAgent) {
        UserAgent = userAgent;
    }

    public String getOrigin() {
        return Origin;
    }

    public void setOrigin(String origin) {
        Origin = origin;
    }

    public String getReferer() {
        return Referer;
    }

    public void setReferer(String referer) {
        Referer = referer;
    }

    public Map<String, String> getHeader() {
        return header;
    }

    public void setHeader(Map<String, String> header) {
        this.header = header;
    }

    public Map<String, String> getCookie() {
        return cookie;
    }

    public void setCookie(Map<String, String> cookie) {
        this.cookie = cookie;
    }
}
