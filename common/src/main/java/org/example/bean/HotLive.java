package org.example.bean;

/**
 * @author Genius
 * @date 2023/07/18 22:24
 **/

/**
 * 热门直播抽象类
 */
public abstract class HotLive {
    private int watcherNum;  //直播间观众数目
    private int liveId;      //直播间ID
    private String liveName; //直播间名字

    private String liver;   //主播
    private String description; //直播间简介

    public HotLive(int watcherNum, int liveId, String liveName, String description) {
        this.watcherNum = watcherNum;
        this.liveId = liveId;
        this.liveName = liveName;
        this.description = description;
    }

    public HotLive(int watcherNum, int liveId, String liveName, String liver, String description) {
        this.watcherNum = watcherNum;
        this.liveId = liveId;
        this.liveName = liveName;
        this.liver = liver;
        this.description = description;
    }

    public int getWatcherNum() {
        return watcherNum;
    }

    public void setWatcherNum(int watcherNum) {
        this.watcherNum = watcherNum;
    }

    public int getLiveId() {
        return liveId;
    }

    public void setLiveId(int liveId) {
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
}
