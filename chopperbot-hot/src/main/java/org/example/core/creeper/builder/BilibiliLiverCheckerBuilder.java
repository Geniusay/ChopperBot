package org.example.core.creeper.builder;

import org.example.bean.FocusLiver;
import org.example.bean.live.BiliBiliLive;
import org.example.core.creeper.loadconfig.BilibiliLiveOnlineConfig;
import org.example.core.creeper.loadconfig.BilibiliLiverCheckerConfig;
import org.example.core.manager.CommonLoadConfigBuilder;
import org.springframework.stereotype.Component;

/**
 * @author Genius
 * @date 2023/09/10 02:51
 **/
@Component
public class BilibiliLiverCheckerBuilder extends CommonLoadConfigBuilder<BilibiliLiverCheckerConfig> {
    @Override
    public BilibiliLiverCheckerConfig build(Object obj) {
        if(obj instanceof FocusLiver){
           return new BilibiliLiverCheckerConfig(((FocusLiver) obj).getLiver());
        }else if(obj instanceof BiliBiliLive){
            return new BilibiliLiverCheckerConfig(((BiliBiliLive) obj).getLiver());
        }
        return null;
    }
}
