package org.example.pojo.download;

import org.example.utils.FormatUtil;

/**
 * @author Genius
 * @date 2023/07/26 15:25
 **/
public abstract class LoadConfig {

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


}
