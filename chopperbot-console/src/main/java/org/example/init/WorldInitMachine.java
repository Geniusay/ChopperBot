package org.example.init;

import org.example.log.ChopperLogFactory;
import org.example.log.LoggerType;
import org.example.thread.ChopperBotGuardPool;
import org.example.thread.oddjob.OddJobBoy;
import org.example.util.ExceptionUtil;
import org.example.util.PluginUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author Genius
 * @date 2023/07/22 17:52
 **/
public class WorldInitMachine extends ModuleInitMachine{

    private static final String githubUrl = "https://github.com/969025903/ChopperBot";

    public WorldInitMachine() throws Exception {
        super("ChopperBot",ChopperLogFactory.getLogger(LoggerType.System));
    }

    public List<CommonInitMachine> alreadyInitModule = new ArrayList<>();

    @Override
    public boolean init() {
        ChopperBotGuardPool.init();
        try {
            initMachines = PluginUtil.getAllModuleInit();
            return initLogger(()->{
                if(checkNeedPlugin()){
                    for (CommonInitMachine initMachine : initMachines) {
                        if ((initMachine).checkNeedPlugin()) {
                            if(!initMachine.init()){
                                return fail();
                            }
                            (initMachine).registerPlugin();
                             alreadyInitModule.add(initMachine);
                        }else{
                            return fail();
                        }
                    }
                    registerPlugin();
                    return success();

                }
                return fail();

            });
        } catch (Exception e) {
            return fail( ExceptionUtil.getCause(e));
        }
    }

    @Override
    public void afterInit() {
        try {
            OddJobBoy.Boy().addWork(
                    ()->{
                        getInitMachines().forEach(
                                InitMachine::afterInit
                        );
                    }
            );
        }catch (Exception e){
            e.printStackTrace();
        }

    }



    @Override
    protected boolean initLogger(Supplier<Boolean> init) {
        logger.info("🌏 <{}> Wake up,Find {} module need to init,please wait.....","ChopperBot",initMachines);
        return init.get();
    }

    @Override
    public void shutdown() {
        logger.info("🌏 <{}> is shutting down,{} modules need to be closed,please wait.....","ChopperBot",getInitMachines().size());

        ChopperBotGuardPool.GuardPool().shutdown();
        this.alreadyInitModule.forEach(
                InitMachine::shutdown
        );

        logger.info("🌏 <{}> all modules have been closed. Good Bye~🤗","ChopperBot");
    }

    @Override
    public void successLog() {
        successLog(String.format("🤖 <%s> Already Start,Click here http://localhost:8888/ to Enjoy it .You can goto the %s to support us,THX 😘! ","ChopperBot",githubUrl));
    }

    @Override
    public void failLog(){
        failLog(String.format("👻 <%s> Fail to Start,Please ensure that your module is correct or you can go to the " +
                "%s write your error info 😥 ! ","ChopperBot",githubUrl));
    }
}
