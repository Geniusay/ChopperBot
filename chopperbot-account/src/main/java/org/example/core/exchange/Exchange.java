package org.example.core.exchange;

import org.example.api.VedioPublishApi;
import org.example.pojo.VideoQueue;
import org.example.pojo.VideoToPublish;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author welsir
 * @Date 2023/9/4 22:07
 */
public class Exchange {
    private final Map<String, List<VideoQueue>> bindings = new HashMap<>();

    ScheduledExecutorService task;
    public Exchange() {
        task = Executors.newScheduledThreadPool(1);
    }

    public void startListening() {
        // 每5秒轮询一次消息队列
        // 检查消息队列并提取消息
        task.scheduleAtFixedRate(this::checkAndProcessMessages, 0, 5, TimeUnit.SECONDS);
    }

    private void checkAndProcessMessages() {
        // 在这里检查消息队列，提取消息并调用publish方法
        for (String routingKey : bindings.keySet()) {
            if (bindings.containsKey(routingKey)) {
                List<VideoQueue> queues = bindings.get(routingKey);
                for (VideoQueue queue : queues) {
                    Object message = queue.dequeue();
                    if (message != null) {
                        VedioPublishApi.publishVideo(new VideoToPublish.Buider(message.toString(),queue.getCookies(),1).build());
                    }
                }
            }
        }
    }

    public void bind(VideoQueue queue, String routingKey) {
        this.bindings.putIfAbsent(routingKey, new ArrayList<>());
        this.bindings.get(routingKey).add(queue);
    }

    public void publish(String routingKey, Object message) {
        if (this.bindings.containsKey(routingKey)) {
            List<VideoQueue> queues = this.bindings.get(routingKey);
            for (VideoQueue queue : queues) {
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
