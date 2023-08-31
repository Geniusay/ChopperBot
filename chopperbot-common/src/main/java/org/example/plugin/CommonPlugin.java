package org.example.plugin;

import org.slf4j.Logger;

import java.util.List;

/**
 * @author Genius
 * @date 2023/07/31 22:55
 **/
public abstract class CommonPlugin implements ChopperBotPlugin{
    private String module;
    private String pluginName;
    private List<String> needPlugins;

    private boolean isAutoStart;

    protected Logger logger;

    @Override
    public boolean init() {
        return true;
    }

    @Override
    public void afterInit() {

    }

    @Override
    public void shutdown() {

    }

    public CommonPlugin(String module, String pluginName, List<String> needPlugins, boolean isAutoStart) {
        this.module = module;
        this.pluginName = pluginName;
        this.needPlugins = needPlugins;
        this.isAutoStart = isAutoStart;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getPluginName() {
        return pluginName;
    }

    public void setPluginName(String pluginName) {
        this.pluginName = pluginName;
    }

    public List<String> getNeedPlugins() {
        return needPlugins;
    }

    public void setNeedPlugins(List<String> needPlugins) {
        this.needPlugins = needPlugins;
    }

    public boolean isAutoStart() {
        return isAutoStart;
    }

    public void setAutoStart(boolean autoStart) {
        isAutoStart = autoStart;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public void info(String msg){
        logger.info("["+pluginName+"] {}",msg);
    }
}
