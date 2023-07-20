package org.example.init;


import org.example.log.InitMachineLogger;

/**
 * @author Genius
 * @date 2023/04/20 19:36
 **/

//模块初始化接口
public interface InitMachine extends InitMachineLogger {

    boolean init();
}
