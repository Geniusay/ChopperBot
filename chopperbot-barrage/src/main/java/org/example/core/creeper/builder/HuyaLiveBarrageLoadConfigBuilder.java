package org.example.core.creeper.builder;

import org.example.bean.live.DouyuLive;
import org.example.bean.live.HuyaLive;
import org.example.core.creeper.loadconfig.DouyuLiveBarrageLoadConfig;
import org.example.core.creeper.loadconfig.HuyaLiveBarrageLoadConfig;
import org.example.core.manager.CommonLoadConfigBuilder;
import org.springframework.stereotype.Component;

/**
 * @author Genius
 * @date 2023/10/16 22:03
 **/

@Component
public class HuyaLiveBarrageLoadConfigBuilder extends CommonLoadConfigBuilder<HuyaLiveBarrageLoadConfig> {
    @Override
    public HuyaLiveBarrageLoadConfig build(Object obj) {
        if(obj instanceof HuyaLive){
            return new HuyaLiveBarrageLoadConfig(((HuyaLive) obj).getLiver(),((HuyaLive) obj).getLiveId());
        }
        return null;
    }
}
