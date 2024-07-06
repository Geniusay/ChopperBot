package org.example.core.guard;

import lombok.extern.slf4j.Slf4j;
import org.example.bean.section.PackageSection;
import org.example.bean.section.VideoSection;
import org.example.core.factory.videoPushFactory.StrategyFactory;
import org.example.plugin.SpringGuardPlugin;
import org.example.pojo.PacketSectionVideo;
import org.example.sql.SQLInitHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description 切片推送通道 负责将切片工厂包装好的切片存放 并给切片打上额外标签
 * @Author welsir
 * @Date 2023/9/4 22:10
 */
@Component
@Slf4j
public class VideoPushChannelGuard extends SpringGuardPlugin {

    StrategyFactory factory;
    BlockingQueue<PackageSection> queue = new ArrayBlockingQueue<>(1024);
    ConcurrentHashMap<String, PacketSectionVideo> videosCollection = new ConcurrentHashMap<>();
    private int val = 0;

    private AtomicInteger count = new AtomicInteger(0);
    @Resource
    SQLInitHelper sqlInitHelper;
    @Override
    public boolean init() {
        factory = StrategyFactory.selectStrategy(val);
        sqlInitHelper.initTable("videoTemp","");
        return true;
    }

    @Override
    public void start() {
        PackageSection p = queue.poll();
        if(p==null){
            return;
        }
        System.out.println(p);
        //数据库持久化保存
        PacketSectionVideo packedVideo = factory.wrapperSectionVideo(p);
        videosCollection.put(packedVideo.getId(),packedVideo);
        count.incrementAndGet();
    }

    public void sendVideo(VideoSection p) {
        queue.add((PackageSection) p);
    }

    public boolean pushNotify(){
        return count.get()!=0;
    }

    public void decrementCount(){
        count.decrementAndGet();
    }
    public List<String> priority(){
        return factory.queryPriority();
    }

    public void editPriority(List<String> priority){
        factory.changePriority(priority);
    }

    public List<PacketSectionVideo> getPacketSection(){
        List<PacketSectionVideo> list = new ArrayList<>();
        videosCollection.values().stream()
                .filter(v -> !v.isFinish())
                .forEach(list::add);
        return list;
    }

}
