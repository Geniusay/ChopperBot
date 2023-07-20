package org.example.init;

/**
 * @author Genius
 * @date 2023/07/21 00:57
 **/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 模块中的小模块初始化抽象类
 */
public abstract class CommonInitMachine implements ComponentInitMachine{

    protected Logger logger;

    public CommonInitMachine(Logger logger){
        this.logger = logger;
    }

    public CommonInitMachine(){
        this.logger = LoggerFactory.getLogger(this.getClass().getName());
    }


    @Override
    public void successLog() {
        successLog(String.format("[✔] {%s} init success!",this.getClass().toString()));
    }

    @Override
    public void successLog(String str) {
        logger.info(str);
    }

    @Override
    public void failLog() {
        failLog(String.format("[❌] {%s} init error!",this.getClass().toString()));
    }

    @Override
    public void failLog(String str) {
        logger.error(str);
    }

    @Override
    public boolean fail(String failCause) {
        failLog(String.format("[❌] {%s} init error! Execption:{}",this.getClass().toString(),failCause));
        return false;
    }

    @Override
    public boolean success() {
        successLog();
        return true;
    }

    public boolean success(String str){
        successLog(str);
        return true;
    }
}
