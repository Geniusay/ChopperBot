package org.example.core.publisher;


import org.example.pojo.VideoToPublish;

/**
 * @author dhx
 * @date 2023/9/18 20:49
 */
public interface PlatformVideoPublisher {
    void publishVideo(VideoToPublish video);
}
