package org.example.core.loadconfig;

import org.example.utils.FormatUtil;

import java.io.Serializable;
import java.util.UUID;

/**
 * LoadTask配置类抽象类，作为任务配置类的最底层
 * @author Genius
 * @date 2023/07/26 15:25
 **/

public abstract class LoadConfig implements Serializable {

    protected String url;

    protected String startTime;

    public LoadConfig() {
        this.startTime = FormatUtil.getNowDate();
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
}
