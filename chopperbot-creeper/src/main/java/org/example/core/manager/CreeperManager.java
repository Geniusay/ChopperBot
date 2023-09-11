package org.example.core.manager;

import org.example.core.loadtask.LoadTask;
import org.example.core.taskcenter.request.ReptileRequest;
import org.example.core.taskcenter.task.ReptileTask;
import org.example.plugin.CommonPlugin;
import org.example.core.loadconfig.LoadConfig;
import org.example.util.ClassUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static org.example.constpool.ConstPool.PROJECT_PATH;

/**
 * @author Genius
 * @date 2023/08/17 23:28
 **/
public class CreeperManager extends CommonPlugin {

    private ArrayList<CreeperBean> creeperBeans;


    public CreeperManager(String module, String pluginName, List<String> needPlugins, boolean isAutoStart) {
        super(module, pluginName, needPlugins, isAutoStart);
        creeperBeans = new ArrayList<>();
    }

    @Override
    public boolean init() {
        Set<Class<?>> creepers = ClassUtil.getAnnotationClass(PROJECT_PATH + ".core.creeper.loadconfig", Creeper.class);
        for (Class<?> creeper : creepers) {

            Creeper annotation = creeper.getAnnotation(Creeper.class);
            String name = annotation.creeperName();
            String description = annotation.creeperDescription();
            boolean discard = annotation.discard();
            String author = annotation.creeperAuthor();
            String groupName = annotation.group();
            int priority = annotation.priority();

            if (CreeperGroupCenter.GroupMap().containsKey(groupName)) {
                AbstractCreeperGroup group = CreeperGroupCenter.GroupMap().get(groupName);
                group.addMember(new AbstractCreeperGroup.CreeperMember(priority, (Class<? extends LoadConfig>) creeper,discard,name));
            }

            creeperBeans.add(new CreeperBean(name,description,author,groupName,discard,priority));
        }
        return true;
    }

    public <T extends LoadTask,V extends LoadConfig> T getLoadTask(V loadConfig){
        if(loadConfig==null){
            return null;
        }
        Class<? extends LoadTask> task = loadConfig.getClass().getAnnotation(Creeper.class).loadTask();
        try {
            T loadTask = (T) task.getDeclaredConstructor(loadConfig.getClass()).newInstance(loadConfig);
            return loadTask;
        }catch (Exception e){
            return null;
        }
    }

    public <T extends LoadTask> T getLoadTask(String groupName){
        Class<? extends LoadConfig> loadConfigClazz = CreeperGroupCenter.getFirstConfig(groupName);
        try {
            LoadConfig loadConfig =  CreeperBuilder.buildLoadConfig(loadConfigClazz);
            return getLoadTask(loadConfig);
        }catch (Exception e){
            return null;
        }
    }


    public <T extends LoadTask> T getLoadTask(String groupName,String creeperName){
        Class<? extends LoadConfig> loadConfigClazz = CreeperGroupCenter.getLoadConfig(groupName,creeperName);
        try {
            LoadConfig loadConfig =  CreeperBuilder.buildLoadConfig(loadConfigClazz);
            return getLoadTask(loadConfig);
        }catch (Exception e){
            return null;
        }
    }


    public <T extends LoadTask> T getLoadTask(String groupName,Object param){
        Class<? extends LoadConfig> loadConfigClazz = CreeperGroupCenter.getFirstConfig(groupName);
        try {
            LoadConfig loadConfig =  CreeperBuilder.buildLoadConfig(loadConfigClazz,param);
            return getLoadTask(loadConfig);
        }catch (Exception e){
            return null;
        }
    }
    public <T extends LoadTask> T getLoadTask(String groupName,String creeperName,Object param){
        Class<? extends LoadConfig> loadConfigClazz = CreeperGroupCenter.getLoadConfig(groupName,creeperName);
        try {
            LoadConfig loadConfig = CreeperBuilder.buildLoadConfig(loadConfigClazz,param);
            return getLoadTask(loadConfig);
        }catch (Exception e){
            return null;
        }
    }

    public ArrayList<CreeperBean> getCreeperBeans() {
        return creeperBeans;
    }


}
