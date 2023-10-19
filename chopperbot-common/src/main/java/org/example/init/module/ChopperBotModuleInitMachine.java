package org.example.init.module;

import org.example.constpool.ModuleName;
import org.example.init.ModuleInitMachine;
import org.example.log.ChopperLogFactory;
import org.example.log.LoggerType;
import org.example.plugin.annotation.Module;
import org.slf4j.Logger;

/**
 * @author Genius
 * @date 2023/10/19 18:36
 **/

@Module
public class ChopperBotModuleInitMachine extends ModuleInitMachine {

    public ChopperBotModuleInitMachine() {
        super(ModuleName.CHOPPER_BOT, ChopperLogFactory.getLogger(LoggerType.System));
    }
}
