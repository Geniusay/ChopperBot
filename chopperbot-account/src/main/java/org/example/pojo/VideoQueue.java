package org.example.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author welsir
 * @Date 2023/9/4 22:09
 */
@Data
public class VideoQueue {
    private String name;
    private List<Object> messages;
    private boolean isStrongMatch;
    private String cookies;

    public VideoQueue(String name, boolean isStrongMatch,String cookies) {
        this.name = name;
        this.messages = new ArrayList<>();
        this.isStrongMatch = isStrongMatch;
        this.cookies = cookies;
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

    public String toString() {
        return "[name:" + this.name + ",message:{" + this.messages + "},isMatch:" + this.isStrongMatch;
    }
}
