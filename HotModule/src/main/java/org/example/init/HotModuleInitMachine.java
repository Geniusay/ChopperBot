package org.example.init;

import org.example.constpool.ConstPool;
import org.example.core.recommend.HeatRecommendation;
import org.example.log.ChopperLogFactory;
import org.example.log.LoggerType;

import java.util.List;

/**
 * @author Genius
 * @date 2023/07/21 00:16
 **/

/**
 * 整个热门模块的模块初始化类
 */
public class HotModuleInitMachine extends ModuleInitMachine{

    public HotModuleInitMachine() {
        super(
                List.of(ConstPool.FILE),
                ChopperLogFactory.getLogger(LoggerType.Hot),
                List.of(
                        new HotModuleConfigInitMachine(),   //热门模块配置文件插件
                        new HotModuleGuardInitMachine(),    //平台热门直播，热门模块监控插件
                        new HeatRecommendationInitMachine() //平台热门直播推送插件
                ),
                ConstPool.HOT
        );
    }
}
