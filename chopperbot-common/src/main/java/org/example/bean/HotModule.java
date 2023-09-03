package org.example.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Genius
 * @date 2023/07/18 20:39
 **/


@Data
public abstract class HotModule<T extends Live> implements Serializable {
    private String tagId;
    private String tagName;

    private List<T> lives;

    public HotModule(String tagId, String tagName){
        this.tagId = tagId;
        this.tagName = tagName;
    }

    public void setHotLives(List<T> lives) {
        this.lives = lives;
    }

    public List<T> getHotLives() {
        return lives;
    }
}
