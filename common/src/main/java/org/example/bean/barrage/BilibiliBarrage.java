package org.example.bean.barrage;

import org.example.bean.Barrage;
import org.example.constpool.ConstPool;

/**
 * @author Genius
 * @date 2023/08/03 18:42
 **/
public class BilibiliBarrage extends Barrage {
    private final String platform = ConstPool.PLATFORM.BILIBILI.getName();

    public BilibiliBarrage() {
    }

    public BilibiliBarrage(String mid, Long timeReal, Long timeIndex, String content) {
        super(mid, timeReal, timeIndex, content);
    }
}
