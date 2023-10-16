package org.example.api;

import org.example.core.factory.platformSelectionFactory;
import org.example.core.publisher.PlatformVideoPublisher;
import org.example.pojo.VideoToPublish;

/**
 * @Description
 * @Author welsir
 * @Date 2023/10/13 17:14
 */
public class VedioPublishApi {

    public static void publishVideo(VideoToPublish videoEntity){
        PlatformVideoPublisher publisher = platformSelectionFactory.creatPlatformPublisher(videoEntity.getPlatform().getId());
        publisher.publishVideo(videoEntity);
    }



}
