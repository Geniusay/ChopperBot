package org.example.core.exchange;



import lombok.extern.slf4j.Slf4j;
import org.example.api.VideoPublishApi;
import org.example.bean.section.PackageSection;
import org.example.core.channel.AccountBindChannel;
import org.example.core.guard.VideoPushChannelGuard;
import org.example.core.route.DefaultRouteRuler;

import org.example.pojo.Account;
import org.example.pojo.PacketSectionVideo;
import org.example.pojo.VideoToPublish;
import org.example.pojo.VideoToPush;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.*;

/**
 * @Description 用户视频管道
 * @Author welsir
 * @Date 2023/9/4 22:07
 */
@Component
@Slf4j
public class Exchange {
    //存储不同管道对应的切片集合
    private final Map<String, List<PacketSectionVideo>> channels = new HashMap<>();
    //存储管道的匹配规则
    private Map<String,String> channelRoute;

    @Resource
    DefaultRouteRuler defaultRouteRuler;
    @Resource
    AccountBindChannel channel;
    @Resource
    VideoPublishApi pushVideo;

    //切片推送
    public void work(){
        Map<String, List<Account>> channelAccount = channel.getChannelAccount();
        channels.forEach((k,v)->{
            if(channels.get(k)==null||channelAccount.get(k)==null){
                return;
            }
            List<Account> accountList = channelAccount.get(k);
            List<PacketSectionVideo> packageSections = channels.get(k);
            for (Account account : accountList) {
                for (PackageSection packageSection : packageSections) {
                    //换成builder
                    VideoToPublish video = new VideoToPublish();
                    video.setCookies(account.getCookies());
                    video.setTitle(packageSection.getTitle());
                    video.setDevicePath(getDevicePath(packageSection.getVideoPath()));
                    video.setVideoPath(packageSection.getVideoPath());
                    video.setContent(packageSection.getContent());
                    video.setCoverPath(packageSection.getCoverPath());
                    video.setDescription(packageSection.getDescription());
                    video.setLabels(packageSection.getLabels());
                    video.setPlatform(String.valueOf(account.getPlatformId()));
                    pushVideo.publishVideo(video);
                }
            }
            channels.get(k).removeAll(v);
        });
    }

    public void pushToQueue(String routingKey,PacketSectionVideo packageSection) {
        channelRoute = channel.getChannelRoute();
        for (String key : channelRoute.keySet()) {
            String route = channelRoute.get(key);
            boolean flag = defaultRouteRuler.matchRoute(routingKey, route);
            if (flag){
                channels.putIfAbsent(key,new ArrayList<>());
                List<PacketSectionVideo> packageSections = channels.get(key);
                packageSections.add(packageSection);
            }
        }
    }

    private String getDevicePath(String filePath){
        Path path = FileSystems.getDefault().getPath(filePath);
        String fileName = path.getFileName().toString();
        Path directoryPath = path.getParent();
        String directory = (directoryPath != null) ? directoryPath.toString() : "No Directory";
        return directory+"\\"+fileName+"\\";
    }
}
