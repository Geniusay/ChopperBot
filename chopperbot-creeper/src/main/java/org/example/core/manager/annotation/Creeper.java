package org.example.core.manager.annotation;

import org.example.core.loadtask.LoadTask;

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

}
