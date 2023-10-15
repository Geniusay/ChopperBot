package org.example.core.factory;

import org.example.core.publisher.PlatformVideoPublisher;
import org.example.core.publisher.impl.BilibiliVideoPublisher;
import org.example.core.publisher.impl.DouyinVideoPublisher;

/**
 * @Description
 * @Author welsir
 * @Date 2023/10/13 21:09
 */
public class platformSelectionFactory {
    public static PlatformVideoPublisher creatPlatformPublisher(int platformId) {
        switch (platformId){
            case 2:
                return new BilibiliVideoPublisher();
            case 3:
                return new DouyinVideoPublisher();
            default:
                throw new IllegalArgumentException("平台参数不正确!");
        }
    }
}
