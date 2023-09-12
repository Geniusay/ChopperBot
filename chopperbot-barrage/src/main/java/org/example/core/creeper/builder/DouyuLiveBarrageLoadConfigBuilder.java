package org.example.core.creeper.builder;

import org.example.bean.live.DouyuLive;
import org.example.core.creeper.loadconfig.BilibiliLiveLoadBarrageConfig;
import org.example.core.creeper.loadconfig.DouyuLiveBarrageLoadConfig;
import org.example.core.manager.CommonLoadConfigBuilder;
import org.springframework.stereotype.Component;

/**
 * @author Genius
 * @date 2023/09/12 23:24
 **/
@Component
public class DouyuLiveBarrageLoadConfigBuilder extends CommonLoadConfigBuilder<DouyuLiveBarrageLoadConfig> {

    @Override
    public DouyuLiveBarrageLoadConfig build(Object obj) {
       if(obj instanceof DouyuLive){
           return new DouyuLiveBarrageLoadConfig(((DouyuLive) obj).getLiver(),((DouyuLive) obj).getLiveId());
       }
       return null;
    }

}
