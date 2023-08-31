package org.example.bean;

/**
 * @author Genius
 * @date 2023/07/18 22:24
 **/

import java.io.Serializable;

/**
 * 热门直播抽象类
 */
public abstract class Live implements Serializable {
    private int watcherNum;  //直播间观众数目
    private String liveId;      //直播间ID
    private String liveName; //直播间名字

    private String liver;   //主播
    private String description; //直播间简介

    private String platform;

    public Live(int watcherNum, String liveId, String liveName, String description) {
        this.watcherNum = watcherNum;
        this.liveId = liveId;
        this.liveName = liveName;
        this.description = description;
    }

    public Live(int watcherNum, String liveId, String liveName, String liver, String description) {
        this.watcherNum = watcherNum;
        this.liveId = liveId;
        this.liveName = liveName;
        this.liver = liver;
        this.description = description;
    }

    public Live(int watcherNum, String liveId, String liveName, String liver, String description, String platform) {
        this.watcherNum = watcherNum;
        this.liveId = liveId;
        this.liveName = liveName;
        this.liver = liver;
        this.description = description;
        this.platform = platform;
    }

    public int getWatcherNum() {
        return watcherNum;
    }

    public void setWatcherNum(int watcherNum) {
        this.watcherNum = watcherNum;
    }

    public String getLiveId() {
        return liveId;
    }

    public void setLiveId(String liveId) {
        this.liveId = liveId;
    }

    public String getLiveName() {
        return liveName;
    }

    public void setLiveName(String liveName) {
        this.liveName = liveName;
    }

    public String getLiver() {
        return liver;
    }

    public void setLiver(String liver) {
        this.liver = liver;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}
