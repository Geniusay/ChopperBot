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

    private ConcurrentHashMap<String,Class<? extends LoadConfig>> nameToLoadTaskMapping;

    private Map<String,CommonLoadConfigBuilder> loadConfigBuilder;

    private ArrayList<CreeperBean> creeperBeans;

    public CreeperManager(String module, String pluginName, List<String> needPlugins, boolean isAutoStart) {
        super(module, pluginName, needPlugins, isAutoStart);
        nameToLoadTaskMapping = new ConcurrentHashMap<>();
        loadConfigBuilder = new HashMap<>();
        creeperBeans = new ArrayList<>();
    }

    @Override
    public boolean init() {
        Set<Class<?>> creepers = ClassUtil.getAnnotationClass(PROJECT_PATH + ".core.creeper.loadconfig", Creeper.class);
        for (Class<?> creeper : creepers) {
            Creeper annotation = creeper.getAnnotation(Creeper.class);
            String name = annotation.creeperName();
            String description = annotation.creeperDescription();
            Class<? extends CommonLoadConfigBuilder> builder = annotation.builder();
            try {
                if(!builder.getName().equals(ValidLoadConfigBuilder.class.getName())){
                    CommonLoadConfigBuilder<? extends LoadConfig> commonLoadConfigBuilder = builder.getDeclaredConstructor().newInstance();
                    loadConfigBuilder.put(name,commonLoadConfigBuilder);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            nameToLoadTaskMapping.put(name, (Class<? extends LoadConfig>) creeper);

            creeperBeans.add(new CreeperBean(name,description));
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

    public <T extends LoadTask> T getLoadTask(String name){
        Class<? extends LoadConfig> loadConfigClazz = nameToLoadTaskMapping.get(name);
        try {
            LoadConfig loadConfig = loadConfigClazz.getDeclaredConstructor().newInstance();
            return getLoadTask(loadConfig);
        }catch (Exception e){
            return null;
        }
    }

    public <T extends LoadConfig> T buildLoadConfig(String name,Object param){
        if(loadConfigBuilder.containsKey(name)) {
            CommonLoadConfigBuilder commonLoadConfigBuilder = loadConfigBuilder.get(name);
            return (T) commonLoadConfigBuilder.build(param);
        }
        return null;
    }

    public <T extends LoadTask> T getLoadTask(String name,Object param){
       return getLoadTask(buildLoadConfig(name,param));
    }

    public ReptileTask getReptileTask(ReptileRequest request){
        LoadTask loadTask = getLoadTask(request.getLoadConfig());
        if(loadTask==null){
            return null;
        }
        return new ReptileTask(loadTask,request);
    }

    public boolean hasLoadTask(String name){
        return nameToLoadTaskMapping.containsKey(name);
    }

    public ArrayList<CreeperBean> getCreeperBeans() {
        return creeperBeans;
    }

    protected class ValidLoadConfigBuilder extends CommonLoadConfigBuilder<LoadConfig>{

        @Override
        public LoadConfig build(Object obj) {
            return null;
        }

    }

}
