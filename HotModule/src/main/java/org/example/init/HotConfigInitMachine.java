package org.example.init;

/**
 * @author Genius
 * @date 2023/07/21 00:18
 **/

import org.example.config.HotModuleConfig;
import org.example.constpool.PluginName;
import org.example.log.ChopperLogFactory;
import org.example.log.LoggerType;

/**
 * 热门模块配置文件初始化机器
 */
public class HotConfigInitMachine extends ConfigInitMachine<HotModuleConfig> {

    public HotConfigInitMachine() {
        super(PluginName.HOT_CONFIG_PLUGIN
                ,new HotModuleConfig()
                ,ChopperLogFactory.getLogger(LoggerType.Hot));
    }

}
