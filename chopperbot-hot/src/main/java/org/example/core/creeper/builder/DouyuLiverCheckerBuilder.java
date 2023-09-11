package org.example.core.creeper.builder;

import org.example.bean.FocusLiver;
import org.example.bean.live.DouyuLive;
import org.example.core.creeper.loadconfig.DouyuLiverCheckerConfig;
import org.example.core.manager.CommonLoadConfigBuilder;
import org.springframework.stereotype.Component;

/**
 * @author Genius
 * @date 2023/09/10 02:54
 **/
@Component
public class DouyuLiverCheckerBuilder extends CommonLoadConfigBuilder<DouyuLiverCheckerConfig> {

    @Override
    public DouyuLiverCheckerConfig build(Object obj) {
        if(obj instanceof FocusLiver){
            return new DouyuLiverCheckerConfig(((FocusLiver) obj).getRoomId());
        }else if(obj instanceof DouyuLive){
            return new DouyuLiverCheckerConfig(((DouyuLive) obj).getLiveId());
        }
        return null;
    }
}
