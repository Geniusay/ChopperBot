package org.example.interceptor;

import org.example.exception.plugin.PluginNotRegisterException;
import org.example.init.InitPluginRegister;
import org.example.plugin.annotation.CheckPlugin;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class PluginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            CheckPlugin checkPlugin = handlerMethod.getBeanType().getAnnotation(CheckPlugin.class);

            if (checkPlugin != null) {
                for (String plugin: checkPlugin.needPlugin()) {
                    if(!InitPluginRegister.isRegister(plugin)){
                        throw new PluginNotRegisterException(checkPlugin.needPlugin());
                    }
                }
            }
        }
        return true;
    }
}
