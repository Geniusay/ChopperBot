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
    private static Map<String,CommonLoadConfigBuilder> builderMap = new HashMap<>();

    @Autowired
    public CreeperBuilder(ApplicationContext context){
        Map<String, CommonLoadConfigBuilder> beans = context.getBeansOfType(CommonLoadConfigBuilder.class);
        beans.forEach((k,v)->{
            builderMap.put(v.getName(),v);
        });
    }

    public static  <T extends LoadConfig> T buildLoadConfig(String name,Object param){
        if(builderMap.containsKey(name)) {
            CommonLoadConfigBuilder commonLoadConfigBuilder = builderMap.get(name);
            return (T) commonLoadConfigBuilder.build(param);
        }
        return null;
    }

}
