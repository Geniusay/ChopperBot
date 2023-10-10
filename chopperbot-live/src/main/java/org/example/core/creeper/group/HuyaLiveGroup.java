package org.example.core.creeper.group;

import org.example.constpool.ConstGroup;
import org.example.constpool.ConstPool;
import org.example.core.manager.AbstractCreeperGroup;

/**
 * @author dhx
 * @date 2023/10/10 10:53
 */
public class HuyaLiveGroup extends AbstractCreeperGroup {
    @Override
    public String getPlatform() {
        return ConstPool.HUYA;
    }

    @Override
    public String getFunctionName() {
        return ConstGroup.LIVE_ONLINE;
    }
}
