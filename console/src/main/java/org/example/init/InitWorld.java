package org.example.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Genius
 * @date 2023/04/20 19:36
 **/

@Component
//初始化整个项目，各个模块配置文件
public class InitWorld {

    private ConfigurableApplicationContext ctx;

    private SystemInitMachine world;

    private InitWorld(ConfigurableApplicationContext ctx) {
        this.ctx = ctx;
        this.world = new SystemInitMachine();
    }

    @PostConstruct
    private void init(){
        boolean isInit = world.init();
        if(!isInit){
            close();
        }
    }

    private void close(){
        world.shutdown();
        int exit = SpringApplication.exit(ctx, () -> 0);
        System.exit(exit);
    }
}
