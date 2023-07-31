package org.example.init;

/**
 * @author Genius
 * @date 2023/07/21 00:57
 **/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 模块中的小模块初始化抽象类
 */
public abstract class CommonInitMachine implements ComponentInitMachine{

    protected List<String> needPlugins; //初始化时需要的插件

    protected String pluginName;

    protected Logger logger;




    /**
     * 注册插件
     */
    public void registerPlugin(){
        InitPluginRegister.registerPluginTable.put(pluginName,this.getClass());
    }

    /**
     * 检查该插件需要的其他插件
     * @return
     */
    @Override
    public boolean checkNeedPlugin() {
        for (String needPlugin : needPlugins) {
            if(!InitPluginRegister.registerPluginTable.containsKey(needPlugin)){
                fail(String.format("Missing {%s} plugin,please check your plugin init!",needPlugin));
                return false;
            }
        }
        return true;
    }


    public CommonInitMachine(List<String> needPlugins, Logger logger, String pluginName) {
        this.needPlugins = needPlugins;
        this.logger = logger;
        this.pluginName = pluginName;
    }

    public CommonInitMachine(Logger logger,String pluginName){
        needPlugins = new ArrayList<>();
        this.logger = logger;
        this.pluginName = pluginName;
    }

    public CommonInitMachine(String pluginName){
        this.pluginName = pluginName;
        needPlugins = new ArrayList<>();
        this.logger = LoggerFactory.getLogger(pluginName);
    }


    @Override
    public void successLog() {
        successLog(String.format("[✔] {%s} init success!",pluginName));
    }

    @Override
    public void successLog(String str) {
        logger.info(str);
    }

    @Override
    public void failLog() {
        failLog(String.format("[❌] {%s} init error!",pluginName));
    }

    @Override
    public void failLog(String str) {
        logger.error(str);
    }

    @Override
    public boolean fail(String failCause) {
        failLog(String.format("[❌] {%s} init error! Execption:{%s}",pluginName,failCause));
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

    @Override
    public void shutdown(){
        shutdownLog();
    }

    private void shutdownLog(){
        logger.info("[🆖] {} close success.",pluginName);
    }

    @Override
    public void afterInit() {

    }

    public List<String> getNeedPlugins() {
        return needPlugins;
    }
}
