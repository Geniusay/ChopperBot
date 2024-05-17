package org.example.core.guard;

import lombok.extern.slf4j.Slf4j;
import org.example.bean.section.PackageSection;
import org.example.core.factory.videoPushFactory.StrategyFactory;
import org.example.plugin.SpringGuardPlugin;
import org.example.pojo.PacketSectionVideo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

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
    int val = 0;

    @Override
    public boolean init() {
        factory = StrategyFactory.connect(val);
        return true;
    }

    @Override
    public void start() {
        PackageSection p = queue.poll();
        if(p==null){
            return;
        }
        PacketSectionVideo packedVideo = factory.rule(p);
        videosCollection.put(packedVideo.getId(),packedVideo);
    }

    public void sendVideo(PackageSection p) {
        queue.add(p);
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
