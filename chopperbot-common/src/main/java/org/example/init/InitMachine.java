package org.example.init;


import java.io.Serializable;

/**
 * @author Genius
 * @date 2023/04/20 19:36
 **/

//模块初始化接口
public interface InitMachine extends Serializable {

    boolean init();

    void afterInit();

    void shutdown();
}
