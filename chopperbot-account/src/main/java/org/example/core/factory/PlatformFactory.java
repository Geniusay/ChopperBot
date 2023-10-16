package org.example.core.factory;

import org.example.core.platform.Bilibili;
import org.example.core.platform.Douyin;
import org.example.pojo.PlatformType;


/**
 * @Description
 * @Author welsir
 * @Date 2023/9/24 21:16
 */
public class PlatformFactory {

    public static PlatformOperation createPlatformOperation(int platformId) {
        switch (PlatformType.getPlatform(platformId)){
            case BILIBILI:
                return new Bilibili();
            case DOUYU:
            case DOUYIN:
                return new Douyin();
            default:
                throw new RuntimeException();
        }
    }
}
