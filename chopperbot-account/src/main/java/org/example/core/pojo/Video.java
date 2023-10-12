package org.example.core.pojo;

import org.example.pojo.VideoType;

import java.util.List;

/**
 * @Description
 * @Author welsir
 * @Date 2023/9/4 22:08
 */
public class Video {
    private Object message;
    private List<VideoType> videoType;

    public Video() {
    }

    public List<VideoType> getVideoType() {
        return this.videoType;
    }

    public void setVideoType(List<VideoType> videoType) {
        this.videoType = videoType;
    }

    public Object getMessage() {
        return this.message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }
}
