package org.example.factory;

import org.example.bean.Barrage;
import org.example.bean.barrage.BilibiliBarrage;
import org.example.bean.barrage.DouyuBarrage;
import org.example.constpool.ConstPool;

/**
 * @author Genius
 * @date 2023/08/06 21:39
 **/
public class BarrageFactory {

    public static Barrage getBarrage(String platform){
        if(ConstPool.PLATFORM.DOUYU.getName().equals(platform)){
            return new DouyuBarrage();
        }else if(ConstPool.PLATFORM.BILIBILI.getName().equals(platform)){
            return new BilibiliBarrage();
        }
        return null;
    }
}
