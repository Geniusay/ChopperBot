package org.example.core.guard;

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
public class VideoPushGuard extends SpringGuardPlugin {

    private Exchange exchange;
    private BlockingQueue<Object> receiveVideo;
    @Resource
    private AccountMapper accountMapper;
    @Resource
    ChannelMapper channelMapper;

    @Override
    public boolean init() {
        // 两件事情
        // 一注册队列
        // 二启动队列监听
        exchange = new Exchange();
        //建表
        receiveVideo = new ArrayBlockingQueue<>(1024);
        return true;
    }

    public void start() {
        try {
            Object videoMsg = receiveVideo.poll(5, TimeUnit.SECONDS);
            if (videoMsg instanceof PackageSection) {
                PackageSection video = (PackageSection) videoMsg;
                StringBuilder route = new StringBuilder();
                for (String label : video.getLabels()) {
                    if(route.length()!=0)
                        route.append(".");
                    route.append(label);
                }
                exchange.publish(route.toString(),video);
            }
            exchange.startListening();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendVideo(Object msg) {
        receiveVideo.offer(msg);
    }

}
