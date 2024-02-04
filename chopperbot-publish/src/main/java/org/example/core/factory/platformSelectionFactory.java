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
    public static PlatformVideoPublisher creatPlatformPublisher(String platform) {
        if("BILIBILI".equals(platform)){
            return new BilibiliVideoPublisher();
        }else if(platform.equals("DOUYIN")){
            return new DouyinVideoPublisher();
        }
        return null;
    }
}
