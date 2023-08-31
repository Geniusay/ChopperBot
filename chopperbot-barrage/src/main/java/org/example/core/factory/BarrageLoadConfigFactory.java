package org.example.core.factory;

import org.example.constpool.ConstPool;
import org.example.core.creeper.loadconfig.BilibiliLiveLoadBarrageConfig;
import org.example.core.creeper.loadconfig.DouyuLiveLoadBarrageConfig;
import org.example.core.creeper.loadconfig.LoadBarrageConfig;

/**
 * @author Genius
 * @date 2023/09/01 00:19
 **/
public class BarrageLoadConfigFactory {

    public static  <T extends LoadBarrageConfig> T buildBarrageConfig(String platform,String liver,String roomId){
        platform = platform.toLowerCase();
        if(ConstPool.PLATFORM.DOUYU.getName().equals(platform)){
            return (T) new DouyuLiveLoadBarrageConfig(liver,roomId);
        }else if(ConstPool.PLATFORM.BILIBILI.getName().equals(platform)){
            return (T) new BilibiliLiveLoadBarrageConfig(liver,roomId);
        }
        return null;
    }
}
