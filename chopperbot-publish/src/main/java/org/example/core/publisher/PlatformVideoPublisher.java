package org.example.core.publisher;

import org.example.pojo.Uploader;

/**
 * @author dhx
 * @date 2023/9/18 20:49
 */
public interface PlatformVideoPublisher<T extends Uploader> {
    void publishVideo(T uploader);
}
