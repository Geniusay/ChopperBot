package org.example.core.creeper.group;

import org.example.constpool.ConstGroup;
import org.example.constpool.ConstPool;
import org.example.core.manager.AbstractCreeperGroup;
import org.springframework.stereotype.Component;

/**
 * @author Genius
 * @date 2023/09/10 01:54
 **/
@Component
public class DouyuLiverCheckerGroup extends AbstractCreeperGroup {
    @Override
    public String getPlatform() {
        return ConstPool.DOUYU;
    }

    @Override
    public String getFunctionName() {
        return ConstGroup.LIVER_CHECKER;
    }
}
