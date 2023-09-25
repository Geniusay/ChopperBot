package org.example.core.factory;

import org.example.core.platform.Bilibili;
import org.example.pojo.PlatformType;

/**
 * @Description
 * @Author welsir
 * @Date 2023/9/24 21:16
 */
public class PlatformFactory {

    public static void createPlatformOperation(int platformId) {
        switch (PlatformType.getPlatform(platformId)){
            case BILIBILI:
                new Bilibili();
                return;
            case DOUYU:
            case DOUYIN:
                return;
            default:
                throw new RuntimeException();
        }
    }
}
