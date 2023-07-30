package org.example.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author Genius
 * @date 2023/04/20 19:36
 **/

@Component
//初始化整个项目，各个模块配置文件
public class InitWorld {

    @Autowired
    ModuleSrcConfigFileInitMachine moduleSrcConfigFileInitMachine;
    private ConfigurableApplicationContext ctx;


    private InitWorld(ConfigurableApplicationContext ctx) {
        this.ctx = ctx;
    }

    @PostConstruct
    private void init(){
        if(moduleSrcConfigFileInitMachine.isInitFlag()){
            WorldInitMachine world = new WorldInitMachine();
            if (world.init()) {
                world.afterInit();
                return;
            }else{
                world.shutdown();
            }
        }
        int exit = SpringApplication.exit(ctx, () -> 0);
        System.exit(exit);
    }

}
