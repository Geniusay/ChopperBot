package org.example.plugin.annotation;

import org.example.plugin.CommonPlugin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Plugin {

    String moduleName();
    String pluginName();

    String pluginName_CN() default "未命名插件"; //插件中文名

    String pluginDescription() default "该插件暂无介绍";       //插件介绍
    String[] needPlugin() default {};

    Class<? extends CommonPlugin> pluginClass();

    boolean autoStart() default true;

    boolean springBootPlugin() default false;           //兼容springboot的插件
}
