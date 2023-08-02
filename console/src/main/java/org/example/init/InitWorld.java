package org.example.init;

import org.example.constpool.GlobalFileCache;
import org.example.exception.FileCacheException;
import org.example.plugin.annotation.Plugin;
import org.example.util.ClassUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.example.constpool.ConstPool.PROJECT_PATH;

/**
 * @author Genius
 * @date 2023/04/20 19:36
 **/

@Component
//初始化整个项目，各个模块配置文件
public class InitWorld {

    @Autowired
    ChopperBotConfigFileInitMachine moduleSrcConfigFileInitMachine;


    private ConfigurableApplicationContext ctx;


    private InitWorld(ConfigurableApplicationContext ctx) {
        this.ctx = ctx;

    }

    private void initPluginStartSetting() throws FileCacheException, InterruptedException {
        GlobalFileCache.ModuleSrcConfigFile.write(InitPluginRegister.pluginStartSetting,"pluginStart");
    }
    @PostConstruct
    private void init(){

        if (!InitPluginRegister.initPluginRegister()) {
            close();
        }

        if(moduleSrcConfigFileInitMachine.isInitFlag()){
            WorldInitMachine world = null;
            try {
                initPluginStartSetting();
                world = new WorldInitMachine();
            } catch (Exception e) {
                close();
                return;
            }
            if (world.init()) {
                world.afterInit();
                return;
            }else{
                world.shutdown();
            }
        }
       close();
    }

    private void getAllPlugin(){
        Set<Class<?>> annotationClass = ClassUtil.getAnnotationClass(PROJECT_PATH + ".init", Plugin.class);
        System.out.println(annotationClass);
    }

    private void close(){
        int exit = SpringApplication.exit(ctx, () -> 0);
        System.exit(exit);
    }
}
