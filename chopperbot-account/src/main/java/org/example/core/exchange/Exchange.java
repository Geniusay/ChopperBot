package org.example.core.exchange;

import org.example.api.VedioPublishApi;
import org.example.bean.section.PackageSection;
import org.example.core.route.DefaultRouteRuler;
import org.example.pojo.Video;
import org.example.pojo.VideoQueue;
import org.example.pojo.VideoToPublish;

import javax.annotation.Resource;
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
    private final Map<String, List<PackageSection>> channels = new HashMap<>();
    private Map<String,String> channelRoute;

    @Resource
    DefaultRouteRuler defaultRouteRuler;

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
//        for (String channel : channels.keySet()) {
//            List<PackageSection> packageSections = channels.get(channel);
//            for (PackageSection packageSection : packageSections) {
//                VedioPublishApi.publishVideo(VideoToPublish.builder()
//                        .videoPath(packageSection.getVideoPath()).cookies().build())
//            }
//        }
    }

    public void publish(String routingKey,PackageSection packageSection) {
        for (String key : channelRoute.keySet()) {
            String route = channelRoute.get(key);
            boolean flag = defaultRouteRuler.matchRoute(routingKey, route);
            if (flag){
                if(channels.get(key)==null){
                    channels.put(key,List.of(packageSection));
                }else {
                    List<PackageSection> packageSections = channels.get(key);
                    packageSections.add(packageSection);
                }
            }
        }
    }
}
