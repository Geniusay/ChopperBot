
package org.example;

import org.example.init.InitWorld;
import org.example.init.ModuleConfigSrcInit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

/**
 * @author Genius
 * @date 2023/04/20 00:16
 **/

@SpringBootApplication
public class ConsoleApplication {

    public static void main(String[] args) {

        if (InitWorld.getInstance()
                .setInitMachines(List.of(new ModuleConfigSrcInit()))
                .start()) {
            SpringApplication.run(ConsoleApplication.class, args);
        }

    }
}
