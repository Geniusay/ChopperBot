package org.example.core.guard;

import lombok.extern.slf4j.Slf4j;
import org.example.bean.section.PackageSection;
import org.example.core.exchange.Exchange;
import org.example.mapper.AccountMapper;
import org.example.mapper.AccountTypeMapper;
import org.example.mapper.ChannelMapper;
import org.example.plugin.SpringGuardPlugin;
import org.example.pojo.*;
import org.example.sql.SQLInitHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author welsir
 * @Date 2023/9/4 22:10
 */
@Component
@Slf4j
public class VideoPushGuard extends SpringGuardPlugin {


    private BlockingQueue<Object> receiveVideo;
    @Resource
    private AccountMapper accountMapper;
    @Resource
    ChannelMapper channelMapper;
    @Resource
    SQLInitHelper sqlInitHelper;
    @Resource
    Exchange exchange;

    @Override
    public boolean init() {
        receiveVideo = new ArrayBlockingQueue<>(1024);
        return true;
    }

    public void start() {
        try {
            log.debug("阻塞队列监听视频...");
            Object videoMsg = receiveVideo.poll(5, TimeUnit.SECONDS);
            if (videoMsg instanceof PackageSection) {
                PackageSection video = (PackageSection) videoMsg;
                StringBuilder route = new StringBuilder();
                for (String label : video.getLabels()) {
                    if(route.length()!=0) {
                        route.append(".");
                    }
                    route.append(label);
                }
                exchange.pushToQueue(route.toString(),video);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendVideo(Object msg) {
        receiveVideo.offer(msg);
    }

}
