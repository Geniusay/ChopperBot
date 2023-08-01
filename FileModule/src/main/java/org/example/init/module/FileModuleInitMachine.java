package org.example.init.module;

import org.example.constpool.ConstPool;
import org.example.constpool.PluginName;
import org.example.init.FileCacheManagerInitMachine;
import org.example.init.ModuleInitMachine;
import org.example.log.ChopperLogFactory;
import org.example.log.LoggerType;
import org.example.plugin.Plugin;

import java.util.List;

/**
 * @author Genius
 * @date 2023/07/22 18:47
 **/

public class FileModuleInitMachine extends ModuleInitMachine {

    public FileModuleInitMachine() {
        super(ConstPool.FILE,  ChopperLogFactory.getLogger(LoggerType.File));
    }

}
