package org.example.aop;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.example.log.ChopperLogFactory;
import org.example.log.LoggerType;
import org.example.sql.SQLInitHelper;
import org.example.sql.annotation.SQLInit;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

/**
 * @author Genius
 * @date 2023/10/19 22:59
 **/
@Aspect
@Component
public class SQLInitAspect {

    @Resource
    SQLInitHelper helper;

    @Resource
    private ApplicationContext context;

    @AfterReturning(value = "@annotation(org.example.sql.annotation.SQLInit)",returning = "list")
    public void init(JoinPoint point,List<?> list){
        Signature signature = point.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        SQLInit ano = method.getAnnotation(SQLInit.class);
        Class<? extends BaseMapper> mapper = ano.mapper();
        String table = ano.table();
        if (!ano.isTable()) {
            if (!helper.initDatabase()) {
                throw new RuntimeException("database create error!");
            }
            return;
        }else if(StringUtils.hasText(table)){
            if(!StringUtils.hasText(ano.tableSQL())) throw new RuntimeException(table+" table create sql invalid!");
            if (!helper.hasTable(table)) {
                if (!helper.initTable(table,ano.tableSQL())) {
                    throw new RuntimeException(table+" table create fail!");
                }
                if(list==null||list.isEmpty()) return;
                if(mapper.equals(BaseMapper.class)){
                    throw new RuntimeException("unknown mapper");
                }else{
                    if (helper.initData(list, context.getBean(mapper))) {
                        return;
                    }
                }
            }else{
                return;
            }
        }
        throw new RuntimeException("database init error");
    }
}
