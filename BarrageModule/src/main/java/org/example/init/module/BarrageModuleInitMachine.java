package org.example.init.module;

import org.example.constpool.ConstPool;
import org.example.constpool.ModuleName;
import org.example.init.ModuleInitMachine;
import org.example.log.ChopperLogFactory;
import org.example.log.LoggerType;
import org.slf4j.Logger;

import java.util.List;

/**
 * <p>用于初始化弹幕模块所需</p>
 *
 * @author welsir
 * @date 2023/7/31 10:11
 */

public class BarrageModuleInitMachine extends ModuleInitMachine {

    public BarrageModuleInitMachine() {
        super(List.of(ConstPool.CREEPER),
                ChopperLogFactory.getLogger(LoggerType.Barrage),
                ModuleName.BARRAGE);
    }
}
