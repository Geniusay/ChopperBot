package org.example.core.publisher;

/**
 * @author dhx
 * @date 2023/9/18 20:49
 */
public interface PlatformVideoPublisher {
    /**
     *
     * @param videoPath 视频本地路径
     * @param devicePath 视频分片后存放路径
     * @param Cookie B站用户Cookie
     * @param coverPath 视频封面本地路径
     */
    void publishVideo(String videoPath,String devicePath,String Cookie,String coverPath);
}
