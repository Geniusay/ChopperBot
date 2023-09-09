package org.example.core.exchange;

import org.example.pojo.VideoQueue;

import java.util.*;

/**
 * @Description
 * @Author welsir
 * @Date 2023/9/4 22:07
 */
public class Exchange {
    private final Map<String, List<VideoQueue>> bindings = new HashMap<>();

    public Exchange() {
    }

    public void bind(VideoQueue queue, String routingKey) {
        this.bindings.putIfAbsent(routingKey, new ArrayList());
       this.bindings.get(routingKey).add(queue);
    }


    public void publish(String routingKey, Object message) {
        if (this.bindings.containsKey(routingKey)) {
            List<VideoQueue> queues = this.bindings.get(routingKey);
            Iterator var4 = queues.iterator();

            while(var4.hasNext()) {
                VideoQueue queue = (VideoQueue)var4.next();
                queue.enqueue(message);
            }
        }
    }

    public boolean isEmpty() {
        return this.bindings.isEmpty();
    }

    public Map<String, List<VideoQueue>> getBindings() {
        return this.bindings;
    }
}
