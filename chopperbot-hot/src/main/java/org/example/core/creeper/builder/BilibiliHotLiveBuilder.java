package org.example.core.creeper.builder;

import org.example.api.HotModuleApi;
import org.example.bean.HotModule;
import org.example.bean.hotmodule.BilibiliHotModule;
import org.example.core.creeper.loadconfig.BilibiliHotLiveConfig;
import org.example.core.manager.CommonLoadConfigBuilder;
import org.springframework.stereotype.Component;

/**
 * @author Genius
 * @date 2023/10/17 01:17
 **/
@Component
public class BilibiliHotLiveBuilder extends CommonLoadConfigBuilder<BilibiliHotLiveConfig> {

    @Override
    public BilibiliHotLiveConfig build(Object obj) {
        if(obj instanceof BilibiliHotModule){
           return new BilibiliHotLiveConfig(((BilibiliHotModule) obj).getParent_id(),((BilibiliHotModule) obj).getAct_id(),1);
        }
        return null;
    }
}
