
package org.example;

import org.example.bean.ModuleSrcConfigFile;
import org.example.cache.FileCache;
import org.example.exception.FileCacheException;
import org.example.init.InitWorld;
import org.example.init.ModuleSrcConfigFileInit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

/**
 * @author Genius
 * @date 2023/04/20 00:16
 **/

@SpringBootApplication
public class ConsoleApplication {

    public static void main(String[] args) throws FileCacheException {

        if (InitWorld.getInstance()
                .setInitMachines(List.of(new ModuleSrcConfigFileInit()))
                .start()) {

            SpringApplication.run(ConsoleApplication.class, args);
        }

    }
}
