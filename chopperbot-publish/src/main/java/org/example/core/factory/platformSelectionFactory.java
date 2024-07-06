package org.example.core.factory;

import org.example.core.publisher.PlatformVideoPublisher;
import org.example.core.publisher.impl.BilibiliVideoPublisher;
import org.example.core.publisher.impl.DouyinVideoPublisher;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author welsir
 * @Date 2023/10/13 21:09
 */
@Component
public class platformSelectionFactory {
    public PlatformVideoPublisher creatPlatformPublisher(String platform) {
        if("1".equals(platform)){
            return new BilibiliVideoPublisher();
        }else if(("2").equals(platform)){
            return new DouyinVideoPublisher();
        }
        return null;
    }
}
