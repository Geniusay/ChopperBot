package org.example.core.creeper.group;

import org.example.constpool.ConstGroup;
import org.example.constpool.ConstPool;
import org.example.core.manager.AbstractCreeperGroup;

/**
 * @author dhx
 * @date 2023/10/16 15:59
 */
public class HuyaLiveBarrageGroup extends AbstractCreeperGroup {
    @Override
    public String getPlatform() {
        return ConstPool.HUYA;
    }

    @Override
    public String getFunctionName() {
        return ConstGroup.BARRAGE_ONLINE;
    }
}
