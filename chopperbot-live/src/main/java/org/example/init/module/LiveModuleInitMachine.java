package org.example.init.module;

import org.example.constpool.ConstPool;
import org.example.constpool.ModuleName;
import org.example.init.ModuleInitMachine;
import org.example.log.ChopperLogFactory;
import org.example.log.LoggerType;
import org.example.plugin.annotation.Module;

import java.util.List;

/**
 * @author Genius
 * @date 2023/07/21 00:16
 **/

/**
 * 整个热门模块的模块初始化类
 */
@Module
public class LiveModuleInitMachine extends ModuleInitMachine {

    public LiveModuleInitMachine() {
        super(
                List.of(ConstPool.FILE),
                ChopperLogFactory.getLogger(LoggerType.LiveRecord),
                ModuleName.LIVE
        );
    }
}
