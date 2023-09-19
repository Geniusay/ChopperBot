package org.example.core.creeper.group;

import org.example.constpool.ConstGroup;
import org.example.constpool.ConstPool;
import org.example.core.manager.AbstractCreeperGroup;
import org.springframework.stereotype.Component;

/**
 * @author Genius
 * @date 2023/09/07 17:31
 **/
@Component
public class BiliBiliLiveGroup extends AbstractCreeperGroup {

    @Override
    public String getPlatform() {
        return ConstPool.BILIBILI;
    }

    @Override
    public String getFunctionName() {
        return ConstGroup.LIVE_ONLINE;
    }
}
