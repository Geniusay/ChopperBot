package org.example.api;

import org.example.core.factory.platformSelectionFactory;
import org.example.core.publisher.PlatformVideoPublisher;
import org.example.pojo.VideoToPublish;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * @Description
 * @Author welsir
 * @Date 2023/10/13 17:14
 */
@Component
public class VideoPublishApi {

    public void publishVideo(VideoToPublish video){
        PlatformVideoPublisher publisher = platformSelectionFactory.creatPlatformPublisher(video.getPlatform());
        publisher.publishVideo(video);
    }

}
