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
    String[] needPlugin() default {};

    Class<? extends CommonPlugin> pluginClass();

    boolean autoStart() default true;
}
