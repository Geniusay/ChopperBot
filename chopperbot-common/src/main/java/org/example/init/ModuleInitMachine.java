package org.example.init;

import org.example.util.PluginUtil;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author Genius
 * @date 2023/07/21 00:39
 **/

/**
 * 模块初始化机器，用于整个模块的初始化
 */
public abstract class ModuleInitMachine extends CommonInitMachine{

    protected List<CommonInitMachine> initMachines = new ArrayList<>();

    protected List<CommonInitMachine> allInitMachines;

    private String moduleName;


    @Override
    public boolean checkNeedPlugin() {
        for (String needPlugin : needPlugins) {
            if(!InitPluginRegister.isRegister(needPlugin)){
                fail(String.format("Missing {%s} module,please check your module init!",needPlugin));
                return false;
            }
        }
        return true;
    }

    @Override
    public void registerPlugin() {
        InitPluginRegister.registerPluginTable.put(moduleName,this);
    }

    @Override
    public boolean init() {
        try {
            allInitMachines =  PluginUtil.getModuleAllPluginInit(moduleName);
            for (CommonInitMachine initMachine : allInitMachines) {
                if(InitPluginRegister.needStart(initMachine.pluginName)){
                    initMachines.add(initMachine);
                }
            }
        } catch (Exception e) {
            return fail(e.getMessage());
        }

        return initLogger(()->{
            if(checkNeedPlugin()){
                    for (CommonInitMachine initMachine : initMachines) {

                            if (initMachine.checkNeedPlugin()) {
                                initMachine.setLogger(logger);
                                if(!initMachine.init()){
                                    return fail();
                                }
                                initMachine.registerPlugin();
                            }else{
                                return false;
                            }
                        }
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


    public ModuleInitMachine(String moduleName,Logger logger){
        super(moduleName,logger);
        this.moduleName = moduleName;
    }

    public ModuleInitMachine(List<String> needPlugins, Logger logger, String moduleName) {
        super(moduleName,needPlugins,logger);
        this.moduleName = moduleName;
    }


    public List<CommonInitMachine> getInitMachines() {
        return initMachines;
    }

    public String getModuleName() {
        return moduleName;
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
