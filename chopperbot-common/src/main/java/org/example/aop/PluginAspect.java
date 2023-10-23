package org.example.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.example.constpool.ModuleName;
import org.example.exception.plugin.PluginNotRegisterException;
import org.example.init.InitPluginRegister;
import org.example.log.ChopperLogFactory;
import org.example.plugin.annotation.CheckPlugin;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


/**
 * @author Genius
 * @date 2023/08/02 16:43
 **/

@Aspect
@Component
public class PluginAspect {

    Logger logger = ChopperLogFactory.getLogger(ModuleName.CHOPPER_BOT);
    @Before("@annotation(org.example.plugin.annotation.CheckPlugin)")
    public void checkPluginRegister(JoinPoint point){
        Signature signature = point.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        CheckPlugin annotation = method.getAnnotation(CheckPlugin.class);

        for (String plugin : annotation.needPlugin()) {
            if(!InitPluginRegister.isRegister(plugin)){
                throw new PluginNotRegisterException(annotation.needPlugin());
            }
        }
    }
}
