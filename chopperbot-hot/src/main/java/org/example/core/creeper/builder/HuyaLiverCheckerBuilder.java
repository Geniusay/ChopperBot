package org.example.core.creeper.builder;

import org.example.bean.FocusLiver;
import org.example.bean.live.BiliBiliLive;
import org.example.bean.live.HuyaLive;
import org.example.core.creeper.loadconfig.BilibiliLiverCheckerConfig;
import org.example.core.creeper.loadconfig.HuyaLiverCheckerConfig;
import org.example.core.manager.CommonLoadConfigBuilder;
import org.springframework.stereotype.Component;

/**
 * @author Genius
 * @date 2023/10/16 23:06
 **/
@Component 
public class HuyaLiverCheckerBuilder extends CommonLoadConfigBuilder<HuyaLiverCheckerConfig> {
    @Override
    public HuyaLiverCheckerConfig build(Object obj) {
        if(obj instanceof FocusLiver){
            return new HuyaLiverCheckerConfig(((FocusLiver) obj).getRoomId());
        }else if(obj instanceof HuyaLive){
            return new HuyaLiverCheckerConfig(((HuyaLive) obj).getLiveId());
        }
        return null;
    }
}
