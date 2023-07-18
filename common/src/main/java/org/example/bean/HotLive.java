package org.example.bean;

/**
 * @author Genius
 * @date 2023/07/18 22:24
 **/

/**
 * 热门直播抽象类
 */
public abstract class HotLive {
    private int watcherNum;
    private int liveId;
    private String liveName;
    private String description;

    public HotLive(int watcherNum, int liveId, String liveName, String description) {
        this.watcherNum = watcherNum;
        this.liveId = liveId;
        this.liveName = liveName;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
