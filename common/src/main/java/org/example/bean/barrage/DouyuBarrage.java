package org.example.bean.barrage;

import org.example.bean.Barrage;
import org.example.constpool.ConstPool;

/**
 * 斗鱼弹幕类
 * @author Genius
 * @date 2023/08/03 13:39
 **/
public class DouyuBarrage extends Barrage {


    private final String platform = ConstPool.PLATFORM.DOUYU.getName();

    public DouyuBarrage(String mid, Long timeReal, Long timeIndex, String content) {
        super(mid, timeReal, timeIndex, content);
    }
}
