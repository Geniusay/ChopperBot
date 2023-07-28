package org.example.bean;

import java.util.List;

/**
 * @author Genius
 * @date 2023/07/18 20:39
 **/


public abstract class HotModule<T> {
    private String tagId;
    private String tagName;

    private List<T> lives;

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

    public List<T> getHotLives() {
        return lives;
    }

    public void setHotLives(List<T> lives) {
        this.lives = lives;
    }

    public HotModule(String tagId, String tagName){
        this.tagId = tagId;
        this.tagName = tagName;
    }

}
