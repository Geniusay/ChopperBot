package org.example.init;

/**
 * @author Genius
 * @date 2023/07/21 00:57
 **/

import org.example.log.ResultLogger;
import org.example.plugin.CommonPlugin;
import org.example.plugin.annotation.Plugin;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * 模块中的小模块初始化抽象类
 */
public abstract class CommonInitMachine implements ComponentInitMachine, ResultLogger {

    protected String moduleName;        //模块名称

    protected String pluginName; //插件名称
    protected List<String> needPlugins = new ArrayList<>(); //初始化时需要的插件
    protected boolean isAutoStart;      //是否自动启动

    protected Class<? extends CommonPlugin> pluginClass; //插件类型

    protected Logger logger;            //日志

    protected CommonPlugin plugin;      //插件类


    public CommonInitMachine(List<String> needPlugins, boolean isAutoStart, String moduleName, String name,Class<? extends CommonPlugin> clazz) {
        this.needPlugins = needPlugins;
        this.isAutoStart = isAutoStart;
        this.moduleName = moduleName;
        pluginName = name;
        pluginClass =clazz;
    }

    public CommonInitMachine(String moduleName, Logger logger) {
        this.moduleName = moduleName;
        this.logger = logger;
        this.needPlugins = new ArrayList<>();
    }

    public CommonInitMachine(String moduleName, List<String> needPlugins, Logger logger) {
        this.moduleName = moduleName;
        this.needPlugins = needPlugins;
        this.logger = logger;
    }

    /**
     * 注册插件
     */
    public void registerPlugin(){
        InitPluginRegister.registerPluginTable.put(pluginName,this);
    }

    /**
     * 检查该插件需要的其他插件
     * @return
     */
    @Override
    public boolean checkNeedPlugin() {
        for (String needPlugin : needPlugins) {
            if(!InitPluginRegister.registerPluginTable.containsKey(needPlugin)){
                fail(String.format("Missing [%s] plugin,please check your plugin init!",needPlugin));
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean init() {
        Plugin ano = this.getClass().getAnnotation(Plugin.class);
        try {
            plugin =  ano.pluginClass()
                    .getDeclaredConstructor(String.class,String.class,List.class,boolean.class)
                    .newInstance(moduleName,pluginName,needPlugins,isAutoStart);
            if (plugin.init()) {
                return success();
            }else{
                return fail();
            }
        }catch (Exception e){
            return fail(e.getMessage());
        }
    }

    @Override
    public void successLog() {
        successLog(String.format("[✔] [%s] init success!",pluginName));
    }
    @Override
    public void successLog(String str) {
        logger.info(str);
    }
    @Override
    public void failLog() {
        failLog(String.format("[❌] [%s] init error!",pluginName));
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
        plugin.shutdown();
        shutdownLog();
    }

    private void shutdownLog(){
        logger.info("[🆖] [{}] close success.",pluginName);
    }

    @Override
    public void afterInit() {
        if(plugin!=null){
            plugin.afterInit();
        }

    }

    public List<String> getNeedPlugins() {
        return needPlugins;
    }
    public CommonPlugin getPlugin() {
        return plugin;
    }
    public boolean isAutoStart() {
        return isAutoStart;
    }
    public String getModuleName() {
        return moduleName;
    }
    public String getPluginName() {
        return pluginName;
    }
    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public Class<? extends CommonPlugin> getPluginClass() {
        return pluginClass;
    }
}
