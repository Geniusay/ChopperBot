package org.example.core.creeper.builder;

import org.example.bean.live.BiliBiliLive;
import org.example.core.creeper.loadconfig.BilibiliLiveLoadBarrageConfig;
import org.example.core.manager.CommonLoadConfigBuilder;

/**
 * @author Genius
 * @date 2023/09/05 18:32
 **/
public class BilibiliLiveBarrageLoadConfigBuilder extends CommonLoadConfigBuilder<BilibiliLiveLoadBarrageConfig> {

    @Override
    public BilibiliLiveLoadBarrageConfig build(Object obj) {
        if(obj instanceof BiliBiliLive){
            return new BilibiliLiveLoadBarrageConfig(((BiliBiliLive) obj).getLiveName(),((BiliBiliLive) obj).getLiveId());
        }
        return null;
    }
}
