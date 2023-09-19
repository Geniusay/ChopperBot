package org.example.core.creeper.builder;

import org.example.bean.live.BiliBiliLive;
import org.example.core.creeper.loadconfig.BilibiliLiveLoadBarrageConfig;
import org.example.core.manager.CommonLoadConfigBuilder;
import org.springframework.stereotype.Component;

/**
 * @author Genius
 * @date 2023/09/05 18:32
 **/
@Component
public class BilibiliLiveBarrageLoadConfigBuilder extends CommonLoadConfigBuilder<BilibiliLiveLoadBarrageConfig> {

    @Override
    public BilibiliLiveLoadBarrageConfig build(Object obj) {
        if(obj instanceof BiliBiliLive){
            BilibiliLiveLoadBarrageConfig config = new BilibiliLiveLoadBarrageConfig(((BiliBiliLive) obj).getLiver(), ((BiliBiliLive) obj).getLiveId());
            return config;
        }
        return null;
    }
}
