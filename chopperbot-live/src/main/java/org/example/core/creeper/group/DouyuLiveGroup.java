package org.example.core.creeper.group;

import org.example.constpool.ConstPool;
import org.example.core.manager.AbstractCreeperGroup;
import org.springframework.stereotype.Component;

/**
 * @author Genius
 * @date 2023/09/07 17:32
 **/
@Component
public class DouyuLiveGroup extends AbstractCreeperGroup {
    @Override
    public String getPlatform() {
        return ConstPool.DOUYU;
    }

    @Override
    public String getFunctionName() {
        return "live";
    }
}
