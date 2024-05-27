package org.example.core.exchange;

import cn.hutool.core.lang.hash.Hash;
import org.example.core.guard.VideoPushChannelGuard;
import org.example.mapper.VideoTemporaryMapper;

import org.example.plugin.SpringBootPlugin;
import org.example.pojo.PacketSectionVideo;
import org.example.pojo.VideoTemp;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author welsir
 * @Date 2024/5/17 15:05
 */
@Component
public class PostWorkerManager extends SpringBootPlugin {

    HashMap<String,PacketSectionVideo>  temporaryPool = new HashMap<>();
    @Resource
    Exchange exchange;
    @Resource
    VideoPushChannelGuard videoStorehouse;
    @Resource
    VideoTemporaryMapper mapper;
    @Override
    public boolean init() {
        startProcess();
        return super.init();
    }

    //todo:想办法把exchange的每个不同类型管道提出来，让一个线程处理
    //一：获取视频集合
    //二：把视频集合分类派发
    //三：暂时存放
    public void startProcess(){
        Timer timer = new Timer();
        TimerTask task = new TimerTask(){
            @Override
            public void run() {
                boolean flag = videoStorehouse.pushNotify();
                if(flag){
                    List<PacketSectionVideo> videoList = videoStorehouse.getPacketSection();
                    for (PacketSectionVideo video : videoList) {
                        String route = String.join(".", video.getLabels());
                        //是否自动推送
                        if(!video.isAuto()){
                            temporaryPool.put(video.getId(),video);
                            continue;
                        }
                        exchange.pushToQueue(route,video);
                        video.setFinish(true);
                        videoStorehouse.decrementCount();
                    }
                }
                //如何保证数据一定会被消费且不会重复消费

                exchange.work();
                saveToLocal();
            }
        };
        timer.scheduleAtFixedRate(task,0,2000);
    }

    public void saveToLocal(){
        temporaryPool.forEach((k,v)->{
            VideoTemp tmp = VideoTemp.builder().id(k).path(v.getVideoPath()).build();
            mapper.insert(tmp);
        });
    }
}
