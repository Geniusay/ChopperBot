package org.example.core.creeper.builder;

import org.example.bean.HotModule;
import org.example.bean.hotmodule.DouyuHotModule;
import org.example.core.creeper.loadconfig.DouyuHotLiveConfig;
import org.example.core.manager.CommonLoadConfigBuilder;
import org.springframework.stereotype.Component;

/**
 * @author Genius
 * @date 2023/10/17 01:18
 **/

@Component
public class DouyuHotLiveBuilder extends CommonLoadConfigBuilder<DouyuHotLiveConfig> {
    @Override
    public DouyuHotLiveConfig build(Object obj) {
        if(obj instanceof DouyuHotModule){
            return new DouyuHotLiveConfig(Integer.parseInt(((DouyuHotModule) obj).getTagId()));
        }
        return null;
    }
}
