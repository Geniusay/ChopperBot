package org.example.bean.barrage;

import org.example.bean.Barrage;
import org.example.constpool.ConstPool;

/**
 * @author dhx
 * @date 2023/10/16 16:19
 */
public class HuyaBarrage extends Barrage {
    private final String platform = ConstPool.PLATFORM.HUYA.getName();

    public HuyaBarrage() {
    }

    public HuyaBarrage(String mid, Long timeReal, Long timeIndex, String content) {
        super(mid, timeReal, timeIndex, content);
    }
}
