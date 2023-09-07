package org.example.core.manager;


import org.example.core.loadconfig.LoadConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Genius
 * @date 2023/09/06 23:32
 **/
@Component
public class CreeperBuilder{
    private static Map<Class<? extends LoadConfig>,CommonLoadConfigBuilder> builderMap = new HashMap<>();

    @Autowired
    public CreeperBuilder(ApplicationContext context){
        Map<String, CommonLoadConfigBuilder> beans = context.getBeansOfType(CommonLoadConfigBuilder.class);
        beans.forEach((k,v)->{
            builderMap.put(v.getLoadConfigClass(),v);
        });
    }

    public static  <T extends LoadConfig> T buildLoadConfig(Class<? extends LoadConfig> clazz,Object param){
        if(builderMap.containsKey(clazz)) {
            CommonLoadConfigBuilder commonLoadConfigBuilder = builderMap.get(clazz);
            return (T) commonLoadConfigBuilder.build(param);
        }
        return null;
    }

    public static <T extends LoadConfig> T buildLoadConfig(Class<? extends LoadConfig> clazz){
        try {
            return (T) clazz.getDeclaredConstructor().newInstance();
        }catch (Exception e){
            return null;
        }
    }

}
