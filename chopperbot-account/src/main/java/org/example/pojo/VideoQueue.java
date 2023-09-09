package org.example.pojo;

import org.example.core.VideoType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @Description
 * @Author welsir
 * @Date 2023/9/4 22:09
 */
public class VideoQueue {
    private String name;
    private List<Object> messages;
    private List<VideoType> type;
    private boolean isStrongMatch;

    public List<VideoType> getType() {
        return this.type;
    }

    public void setType(List<VideoType> type) {
        this.type = type;
    }

    public VideoQueue() {
    }

    public VideoQueue(String name, boolean isStrongMatch) {
        this.name = name;
        this.messages = new ArrayList<>();
        this.isStrongMatch = isStrongMatch;
    }

    public void enqueue(Object message) {
        this.messages.add(message);
    }

    public Object dequeue() {
        return !this.isEmpty() ? this.messages.remove(0) : null;
    }

    public boolean isEmpty() {
        return this.messages.isEmpty();
    }

    public boolean shouldReceiveMessage(List<VideoType> videoType) {
        return (new HashSet(videoType)).equals(new HashSet(this.type));
    }

    public String toString() {
        return "[name:" + this.name + ",message:{" + this.messages + "},isMatch:" + this.isStrongMatch;
    }
}
