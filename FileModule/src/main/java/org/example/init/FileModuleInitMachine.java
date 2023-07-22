package org.example.init;

import org.example.log.FileModuleLogger;
import org.slf4j.Logger;

import java.util.List;

/**
 * @author Genius
 * @date 2023/07/22 18:47
 **/
public class FileModuleInitMachine extends ModuleInitMachine{

    public FileModuleInitMachine() {
        super(List.of(
                new ModuleSrcConfigFileInit(),
                new FileCacheManagerInit()
        ), "FileModule", FileModuleLogger.logger);
    }

}
