package org.example.core.creeper.builder;

import org.example.bean.FocusLiver;
import org.example.bean.live.DouyinLive;
import org.example.core.creeper.loadconfig.DouyinLiverCheckerConfig;
import org.example.core.manager.CommonLoadConfigBuilder;
import org.springframework.stereotype.Component;

/**
 * @author dhx
 * @date 2024/5/26 11:07
 */
@Component
public class DouyinLiverCheckerBuilder extends CommonLoadConfigBuilder<DouyinLiverCheckerConfig> {
    @Override
    public DouyinLiverCheckerConfig build(Object obj) {
        if(obj instanceof FocusLiver){
            return new DouyinLiverCheckerConfig(((FocusLiver) obj).getRoomId());
        }else if(obj instanceof DouyinLive){
            return new DouyinLiverCheckerConfig(((DouyinLive) obj).getLiveId());
        }
        return null;
    }
}
