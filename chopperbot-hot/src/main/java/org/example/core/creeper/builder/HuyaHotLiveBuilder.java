package org.example.core.creeper.builder;

import org.example.bean.HotModule;
import org.example.bean.hotmodule.HuyaHotModule;
import org.example.core.creeper.loadconfig.HuyaHotLiveConfig;
import org.example.core.manager.CommonLoadConfigBuilder;
import org.springframework.stereotype.Component;

/**
 * @author Genius
 * @date 2023/10/17 01:17
 **/
@Component
public class HuyaHotLiveBuilder extends CommonLoadConfigBuilder<HuyaHotLiveConfig> {

    @Override
    public HuyaHotLiveConfig build(Object obj) {
        if(obj instanceof HuyaHotModule){
            return new HuyaHotLiveConfig(((HuyaHotModule) obj).getTagId());
        }
        return null;
    }
}
