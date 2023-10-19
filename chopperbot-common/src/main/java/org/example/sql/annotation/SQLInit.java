package org.example.sql.annotation;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SQLInit {

    boolean isTable() default true;

    String table() default "";
    String tableSQL() default "";

    Class<? extends BaseMapper> mapper() default BaseMapper.class;
}
