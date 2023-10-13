package org.example.init.module;

import org.example.init.ModuleInitMachine;
import org.example.log.ChopperLogFactory;
import org.example.log.LoggerType;
import org.example.plugin.annotation.Module;

import java.util.List;

import static org.example.constpool.ConstPool.*;

/**
 * @author Genius
 * @date 2023/07/29 01:58
 **/
@Module
public class CreeperModuleInitMachine extends ModuleInitMachine {
    public CreeperModuleInitMachine() {
        super(List.of(FILE,LIVE_RECORD), ChopperLogFactory.getLogger(LoggerType.Creeper), CREEPER);
    }

}
