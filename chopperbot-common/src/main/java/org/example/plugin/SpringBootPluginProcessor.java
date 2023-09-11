package org.example.plugin;

import org.example.init.SpringPlugInitMachine;
import org.example.plugin.annotation.Plugin;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Genius
 * @date 2023/09/09 23:56
 **/
@Component
public class SpringBootPluginProcessor implements BeanPostProcessor {

    @Resource
    ApplicationContext ctx;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean.getClass().isAnnotationPresent(Plugin.class)){
            Plugin ano = bean.getClass().getAnnotation(Plugin.class);
            if(ano.springBootPlugin()){
                if(bean instanceof SpringPlugInitMachine){
                    String module = ano.moduleName();
                    String pluginName = ano.pluginName();
                    List<String> needPlugins = List.of(ano.needPlugin());
                    boolean autoStart = ano.autoStart();
                    String description = ano.pluginDescription();
                    String pluginNameCn = ano.pluginName_CN();
                    Class<? extends CommonPlugin> pluginClass = ano.pluginClass();
                    ((SpringPlugInitMachine) bean).setEnv(needPlugins,autoStart,module,pluginName,pluginClass);
                    ((SpringPlugInitMachine) bean).setPluginDescription(description);
                    ((SpringPlugInitMachine) bean).setPluginName_CN(pluginNameCn);
                    CommonPlugin plugin = ctx.getBean(pluginClass);
                    ((SpringPlugInitMachine) bean).setPlugin((SpringBootPlugin) plugin);
                }
            }
        }
        return bean;
    }
}
