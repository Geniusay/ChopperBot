package org.example.core.creeper.group;

import org.example.constpool.ConstPool;
import org.example.core.manager.AbstractCreeperGroup;
import org.springframework.stereotype.Component;

/**
 * @author Genius
 * @date 2023/09/07 17:10
 **/
@Component
public class DouyuHotLiveGroup extends AbstractCreeperGroup {


    @Override
    public String getPlatform() {
        return ConstPool.PLATFORM.DOUYU.getName();
    }

    @Override
    public String getFunctionName() {
        return "hot_live";
    }
}
