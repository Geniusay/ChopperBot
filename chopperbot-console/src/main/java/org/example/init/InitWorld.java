package org.example.init;

import org.example.cache.FileCache;
import org.example.cache.FileCacheManagerInstance;
import org.example.config.HotModuleConfig;
import org.example.constpool.GlobalFileCache;
import org.example.core.creeper.loadconfig.DouyuHotModuleConfig;
import org.example.core.manager.CreeperManager;
import org.example.exception.FileCacheException;
import org.example.log.ChopperLogFactory;
import org.example.log.LoggerType;
import org.example.plugin.annotation.Plugin;
import org.example.util.ClassUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.example.constpool.ConstPool.PROJECT_PATH;

/**
 * @author Genius
 * @date 2023/04/20 19:36
 **/
//初始化整个项目，各个模块配置文件
@Component
public class InitWorld implements CommandLineRunner {

    @Autowired
    ChopperBotConfigFileInitMachine moduleSrcConfigFileInitMachine;


    private ApplicationContext ctx;


    private InitWorld(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    private void initPluginStartSetting() throws FileCacheException, InterruptedException {
        GlobalFileCache.ModuleSrcConfigFile.write(InitPluginRegister.pluginStartSetting,"pluginStart");
    }
//    @PostConstruct
//    private void init() throws FileCacheException, InterruptedException {
//
//
//    }

    private void getAllPlugin(){
        Set<Class<?>> annotationClass = ClassUtil.getAnnotationClass(PROJECT_PATH + ".init", Plugin.class);
        System.out.println(annotationClass);
    }

    private void close(){
        int exit = SpringApplication.exit(ctx, () -> 0);
        System.exit(exit);
    }


    @Override
    public void run(String... args) throws Exception {
        try {
            if (!InitPluginRegister.initPluginRegister(ctx)) {
                close();
            }
        }catch (Exception e){
            ChopperLogFactory.getLogger(LoggerType.System).error("Init Plugins Error:{}",e.getMessage());
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
}
