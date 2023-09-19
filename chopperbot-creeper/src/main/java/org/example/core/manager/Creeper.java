package org.example.core.manager;

import org.example.core.loadtask.LoadTask;
import org.example.core.manager.CommonLoadConfigBuilder;
import org.example.core.manager.CreeperManager;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Creeper {

    String creeperName();

    Class<? extends LoadTask> loadTask();

    String creeperDescription() default "该爬虫暂无介绍";       //插件介绍

    String creeperAuthor() default "ChopperBot";

    int priority() default 5;           //优先级

    String group() default "";

    String platform() default "";
    boolean discard() default false;

}
