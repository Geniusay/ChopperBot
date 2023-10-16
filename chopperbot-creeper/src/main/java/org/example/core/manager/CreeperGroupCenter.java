package org.example.core.manager;

import lombok.Data;
import org.example.constpool.ConstGroup;
import org.example.core.loadconfig.LoadConfig;
import org.example.util.ClassUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.example.constpool.ConstPool.PROJECT_PATH;

/**
 * @author Genius
 * @date 2023/09/07 15:06
 **/
@Component
public class CreeperGroupCenter implements Serializable {
    private static Map<String,AbstractCreeperGroup> groupMap = new HashMap<>();

    @Autowired
    public CreeperGroupCenter(ApplicationContext context){
        Set<Class<?>> creepers = ClassUtil.getAnnotationClass(PROJECT_PATH + ".core.creeper.loadconfig", Creeper.class);
        for (Class<?> creeper : creepers) {
            Creeper annotation = creeper.getAnnotation(Creeper.class);
            String name = annotation.creeperName();
            boolean discard = annotation.discard();
            String platform = annotation.platform();
            String groupName = StringUtils.hasText(platform)?CreeperGroupCenter.getGroupName(platform,annotation.group()):annotation.group();
            int priority = annotation.priority();

            AbstractCreeperGroup.CreeperMember member = new AbstractCreeperGroup.CreeperMember(priority, (Class<? extends LoadConfig>) creeper, discard, name);
            if (CreeperGroupCenter.GroupMap().containsKey(groupName)) {
                AbstractCreeperGroup group = CreeperGroupCenter.GroupMap().get(groupName);
                group.addMember(member);
            }else{
                AbstractCreeperGroup group = new AbstractCreeperGroup();
                groupMap.put(groupName,group);
                group.addMember(member);
            }
        }
    }

    public static Map<String,AbstractCreeperGroup> GroupMap(){
        return groupMap;
    }
    /**
     * 默认获取优先级最高的爬虫
     * @param groupName
     * @return
     */
    public static Class<? extends LoadConfig> getLoadConfig(String groupName,String configName){
        if(groupMap.containsKey(groupName)){
            AbstractCreeperGroup abstractCreeperGroup = groupMap.get(groupName);
            return abstractCreeperGroup.getLoadConfig(configName);
        }
        return null;
    }

    /**
     * 获取组别下的名为{configName}的爬虫配置文件
     * @param groupName
     * @return
     */
    public static Class<? extends LoadConfig> getFirstConfig(String groupName){
        if(groupMap.containsKey(groupName)){
            AbstractCreeperGroup abstractCreeperGroup = groupMap.get(groupName);
            return abstractCreeperGroup.getFirstConfig();
        }else{
            return null;
        }
    }


    public static String getGroupName(String platform,String func){
        return platform+"_"+func;
    }

}
