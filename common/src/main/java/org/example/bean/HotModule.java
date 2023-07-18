package org.example.bean;

import lombok.Data;

import java.util.List;

/**
 * @author Genius
 * @date 2023/07/18 20:39
 **/


public abstract class HotModule {
    private String tagId;
    private String tagName;

    private List<HotLive> hotLives;

    public String getTagId() {
        return tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public List<HotLive> getHotLives() {
        return hotLives;
    }

    public void setHotLives(List<HotLive> hotLives) {
        this.hotLives = hotLives;
    }

    public HotModule(String tagId, String tagName){
        this.tagId = tagId;
        this.tagName = tagName;
    }

}
