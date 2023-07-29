package org.example.init;

import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Genius
 * @date 2023/07/21 00:39
 **/

/**
 * 模块初始化机器，用于整个模块的初始化
 */
public abstract class ModuleInitMachine extends CommonInitMachine{

    private List<InitMachine> initMachines;

    private String moduleName;


    @Override
    public boolean checkNeedPlugin() {
        for (String needPlugin : needPlugins) {
            if(!InitPluginRegister.registerPluginTable.containsKey(needPlugin)){
                fail(String.format("Missing {%s} module,please check your module init!",needPlugin));
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean init() {
        return initLogger(()->{
            if(checkNeedPlugin()){
                for (InitMachine initMachine : this.getInitMachines()) {
                    if(!initMachine.init()){
                        return fail();
                    }
                }
                registerPlugin();
                return success();
            }
            return false;

        });
    }



    @Override
    public void afterInit() {
        for(InitMachine initMachine:this.getInitMachines()){
            initMachine.afterInit();
        }
    }

    public ModuleInitMachine(List<InitMachine> initMachines, String moduleName) {
        super(moduleName);
        this.initMachines = initMachines;
        this.moduleName = moduleName;
    }

    public ModuleInitMachine(List<InitMachine> initMachines,String moduleName,Logger logger){
        super(logger,moduleName);
        this.initMachines = initMachines;
        this.moduleName = moduleName;
    }

    public ModuleInitMachine(List<String> needPlugins, Logger logger, List<InitMachine> initMachines, String moduleName) {
        super(needPlugins, logger, moduleName);
        this.initMachines = initMachines;
        this.moduleName = moduleName;
    }

    public List<InitMachine> getInitMachines() {
        return initMachines;
    }

    public void setInitMachines(List<InitMachine> initMachines) {
        this.initMachines = initMachines;
    }


    @Override
    public void successLog() {
        successLog(String.format("✅ <%s> init success! init %s plugins ! ",moduleName,initMachines.size()));
    }

    @Override
    public void successLog(String str) {
        logger.info(str);
    }

    @Override
    public void failLog() {
        failLog(String.format("⛔ <%s> init error!",moduleName));
    }

    @Override
    public void failLog(String str) {
        logger.info(str);
    }


    protected boolean initLogger(Supplier<Boolean> init){
        logger.info("🕑 <{}> start to init,total plugins:{}",moduleName,initMachines.size());
        return init.get();
    }

    @Override
    public boolean fail() {
        failLog();
        return false;
    }

    @Override
    public boolean fail(String failCause) {
        failLog(String.format("⛔ <%s> init error! Execption:%s",moduleName,failCause));
        return false;
    }

    @Override
    public boolean success() {
        successLog();
        return true;
    }

    @Override
    public void shutdown() {
        logger.info("👇 <{}> is shutting down , {} plugins need to shut down...",moduleName,initMachines.size());
        initMachines.forEach(
                InitMachine::shutdown
        );
        logger.info("👆 <{}> Completing the shutdown of all plugins!",moduleName);
    }
}
