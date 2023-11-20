package org.example.core.exchange;



import lombok.extern.slf4j.Slf4j;
import org.example.api.VideoPublishApi;
import org.example.bean.section.PackageSection;
import org.example.core.channel.AccountBindChannel;
import org.example.core.route.DefaultRouteRuler;

import org.example.pojo.Account;
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
 * @Description 视频管道交换机
 * @Author welsir
 * @Date 2023/9/4 22:07
 */
@Component
@Slf4j
public class Exchange {
    //存储不同管道对应的切片集合
    private final Map<String, List<PackageSection>> channels = new HashMap<>();
    //存储管道的匹配规则
    private Map<String,String> channelRoute;

    @Resource
    DefaultRouteRuler defaultRouteRuler;
    @Resource
    AccountBindChannel channel;
    @Resource
    VideoPublishApi pushVideo;

    @Scheduled(fixedDelay = 5000)
    public void publishVideo(){
        log.info("listen video to push...");
        Map<String, List<Account>> channelAccount = channel.getChannelAccount();
        channels.forEach((k,v)->{
            if(channels.get(k)==null){
                return;
            }
            log.debug("channel:"+k+" videos:"+ Arrays.toString(v.toArray()));
            List<Account> accountList = channelAccount.get(k);
            List<PackageSection> packageSections = channels.get(k);
            for (Account account : accountList) {
                for (PackageSection packageSection : packageSections) {
                    VideoToPublish video = new VideoToPublish();
                    video.setCookies(account.getCookies());
                    video.setTitle(packageSection.getTitle());
                    video.setDevicePath(getDevicePath(packageSection.getVideoPath()));
                    video.setVideoPath(packageSection.getVideoPath());
                    video.setContent(packageSection.getContent());
                    video.setCoverPath(packageSection.getCoverPath());
                    video.setDescription(packageSection.getDescription());
                    video.setLabels(packageSection.getLabels());
                    video.setPlatform(packageSection.getPlatform());
                    pushVideo.publishVideo(video);
                }
            }
            channels.get(k).removeAll(v);
        });
    }


    public void pushToQueue(String routingKey,PackageSection packageSection) {
        channelRoute = channel.getChannelRoute();
        for (String key : channelRoute.keySet()) {
            String route = channelRoute.get(key);
            boolean flag = defaultRouteRuler.matchRoute(routingKey, route);
            if (flag){
                channels.putIfAbsent(key,new ArrayList<>());
                List<PackageSection> packageSections = channels.get(key);
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
