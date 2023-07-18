package org.example.bean.hotmodule;

import org.example.bean.HotLive;

/**
 * @author Genius
 * @date 2023/07/19 01:16
 **/
public class DouyuHotLive extends HotLive {

    private int hotTag;

    private int isAd;


    private int type;

    private String url;

    public DouyuHotLive(int watcherNum, int liveId, String liveName, String description,
                        int hotTag, int isAd,  int type, String url) {
        super(watcherNum, liveId, liveName, description);
        this.hotTag = hotTag;
        this.isAd = isAd;
        this.type = type;
        this.url = url;
    }

    public int getHotTag() {
        return hotTag;
    }

    public void setHotTag(int hotTag) {
        this.hotTag = hotTag;
    }

    public int getIsAd() {
        return isAd;
    }

    public void setIsAd(int isAd) {
        this.isAd = isAd;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
