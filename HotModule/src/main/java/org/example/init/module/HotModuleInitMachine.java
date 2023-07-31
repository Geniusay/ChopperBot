package org.example.init.module;

import org.example.constpool.ConstPool;
import org.example.init.HeatRecommendationInitMachine;
import org.example.init.HotConfigInitMachine;
import org.example.init.HotGuardInitMachine;
import org.example.init.ModuleInitMachine;
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
public class HotModuleInitMachine extends ModuleInitMachine {

    public HotModuleInitMachine() {
        super(
                List.of(ConstPool.FILE,ConstPool.CREEPER),
                ChopperLogFactory.getLogger(LoggerType.Hot),
                List.of(
                        new HotConfigInitMachine(),   //热门模块配置文件插件
                        new HotGuardInitMachine(),    //平台热门直播，热门模块监控插件
                        new HeatRecommendationInitMachine() //平台热门直播推送插件
                ),
                ConstPool.HOT
        );
    }
}
