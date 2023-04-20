package org.example.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Genius
 * @date 2023/04/20 19:36
 **/

//初始化整个项目，各个模块配置文件
public class InitWorld {

    private Logger logger = LoggerFactory.getLogger(InitWorld.class);
    private static volatile InitWorld initWorld = new InitWorld();
    private List<InitMachine> initMachines;

    private InitWorld(){
    }

    public InitWorld setInitMachines(List<InitMachine> initMachines){
        this.initMachines = initMachines;
        return this;
    }

    public static InitWorld getInstance(){
        return initWorld;
    }

    public boolean start(){
        if(initWorld == null){
            logger.error("已经初始化过了");
            return true;
        }
        for(InitMachine initMachine : initMachines){
            if(!initMachine.init()){
                logger.error("{}初始化失败!!", initMachine.getClass().getName());
                return false;
            }
        }

        return true;
    }
}
