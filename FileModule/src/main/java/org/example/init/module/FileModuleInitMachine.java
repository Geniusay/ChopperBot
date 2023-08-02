package org.example.init.module;

import org.example.constpool.ConstPool;
import org.example.constpool.ModuleName;
import org.example.init.ModuleInitMachine;
import org.example.log.ChopperLogFactory;
import org.example.log.LoggerType;

/**
 * @author Genius
 * @date 2023/07/22 18:47
 **/

public class FileModuleInitMachine extends ModuleInitMachine {

    public FileModuleInitMachine() {
        super(ModuleName.FILE,  ChopperLogFactory.getLogger(LoggerType.File));
    }

}
