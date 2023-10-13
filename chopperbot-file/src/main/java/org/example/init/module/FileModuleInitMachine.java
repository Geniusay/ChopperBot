package org.example.init.module;

import org.example.constpool.ModuleName;
import org.example.init.ModuleInitMachine;
import org.example.log.ChopperLogFactory;
import org.example.log.LoggerType;
import org.example.plugin.annotation.Module;

/**
 * @author Genius
 * @date 2023/07/22 18:47
 **/

@Module
public class FileModuleInitMachine extends ModuleInitMachine {

    public FileModuleInitMachine() {
        super(ModuleName.FILE,  ChopperLogFactory.getLogger(LoggerType.File));
    }

}
