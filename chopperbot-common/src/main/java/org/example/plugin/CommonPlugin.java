package org.example.plugin;

import lombok.Data;
import org.example.constpool.PluginName;
import org.example.init.InitPluginRegister;
import org.example.log.notice.Notice;
import org.example.log.notice.NoticeHorn;
import org.example.log.notice.NoticePlugin;
import org.example.log.notice.NoticeType;
import org.slf4j.Logger;

import java.util.List;
import java.util.Optional;

/**
 * @author Genius
 * @date 2023/07/31 22:55
 **/
@Data
public abstract class CommonPlugin implements ChopperBotPlugin, NoticeHorn {
    protected String module;
    protected String pluginName;
    protected List<String> needPlugins;
    protected boolean isAutoStart;
    protected Logger logger;
    protected boolean ignore = false;
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
    public void error(String msg){
        logger.error("["+pluginName+"] {}",msg);
    }

    public void warn(String msg){
        logger.warn("["+pluginName+"] {}",msg);
    }

    @Override
    public void info(String msg, boolean isNotice) {
        this.info(msg);
        if(isNotice){
            Optional.ofNullable(InitPluginRegister.getPlugin(PluginName.NOTICE_PLUGIN, NoticePlugin.class))
                    .ifPresent(plugin->{
                        plugin.notice(new Notice().info().title(pluginName).from(pluginName).content(msg));
                    });
        }
    }

    @Override
    public void error(String msg, boolean isNotice) {
        this.error(msg);
        if(isNotice){
            Optional.ofNullable(InitPluginRegister.getPlugin(PluginName.NOTICE_PLUGIN, NoticePlugin.class))
                    .ifPresent(plugin->{
                        plugin.notice( new Notice().error().title(pluginName).from(pluginName).content(msg));
                    });
        }
    }

    @Override
    public void warn(String msg, boolean isNotice) {
        this.warn(msg);
        if(isNotice){
            Optional.ofNullable(InitPluginRegister.getPlugin(PluginName.NOTICE_PLUGIN, NoticePlugin.class))
                    .ifPresent(plugin->{
                        plugin.notice(new Notice().warn().title(pluginName).from(pluginName).content(msg));
                    });
        }
    }
}
