package org.example.init;

/**
 * @author Genius
 * @date 2023/07/29 15:21
 **/

import org.example.constpool.ConstPool;
import org.example.plugin.CommonPlugin;
import org.example.plugin.Plugin;
import org.example.util.ClassUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 插件注册中心
 */
public class InitPluginRegister {

    public static ConcurrentHashMap<String,List<String>> modulePlugin = new ConcurrentHashMap<>();

    public static ConcurrentHashMap<String, List<String>> fatherAndSonPlugin = new ConcurrentHashMap<>();

    public static ConcurrentHashMap<String,List<String>> moduleFatherAndSonPlugin = new ConcurrentHashMap<>();

    public static ConcurrentHashMap<String, CommonInitMachine> allPlugins = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<String,CommonInitMachine> registerPluginTable = new ConcurrentHashMap<>();

    public static Map<String,Boolean> pluginSetting;

    public static boolean initPluginRegister(){
        Set<Class<?>> initMachineClasses = ClassUtil.getAnnotationClass(ConstPool.PROJECT_PATH + ".init", Plugin.class);
        for (Class<?> initMachineClass : initMachineClasses) {
            try {
                Plugin ano = initMachineClass.getAnnotation(Plugin.class);
                String moduleName = ano.moduleName();
                String pluginName = ano.pluginName();
                List<String> needPlugins = List.of(ano.needPlugin());
                boolean autoStart = pluginSetting.containsKey(pluginName)?pluginSetting.get(pluginName):ano.autoStart();
                CommonInitMachine initMachine = (CommonInitMachine) initMachineClass
                        .getDeclaredConstructor(List.class,boolean.class,String.class,String.class,Class.class)
                        .newInstance(needPlugins,autoStart,moduleName,pluginName,ano.pluginClass());

                if(allPlugins.containsKey(pluginName))return false;
                allPlugins.put(pluginName,initMachine);

                if(modulePlugin.containsKey(moduleName)){
                    modulePlugin.get(moduleName).add(pluginName);
                }else{
                    ArrayList<String> list = new ArrayList<>();
                    list.add(pluginName);
                    modulePlugin.put(moduleName,list);
                }
                if(!fatherAndSonPlugin.containsKey(pluginName)){
                    fatherAndSonPlugin.put(pluginName,new ArrayList<>());
                }
                for (String father : needPlugins) {
                    if(fatherAndSonPlugin.containsKey(father)){
                        fatherAndSonPlugin.get(father).add(pluginName);
                    }else{
                        ArrayList<String> list = new ArrayList<>();
                        list.add(pluginName);
                        fatherAndSonPlugin.put(father,list);
                    }
                }
            } catch (Exception e) {
                return false;
            }
        }
        try {
            allPlugins.forEach(
                    (k,v)->{
                        List<String> list = fatherAndSonPlugin.get(k);
                        if(list!=null){
                            List<String> newList = new ArrayList<>();
                            String moduleName = v.getModuleName();
                            List<String> modulePlugins = modulePlugin.get(moduleName);
                            if(modulePlugins!=null){
                                for (String s : list) {
                                    if(modulePlugins.contains(s)){
                                        newList.add(s);
                                    }
                                }
                                moduleFatherAndSonPlugin.put(k,newList);
                            }
                        }

                    }
            );
        }catch (Exception e){
            e.printStackTrace();
        }

        return true;
    }


    public static boolean isRegister(String pluginName){
        return registerPluginTable.containsKey(pluginName);
    }

    public static boolean closePlugin(String pluginName){
        synchronized (pluginName) {
            if (registerPluginTable.containsKey(pluginName)) {
                for (String plugin : fatherAndSonPlugin.get(pluginName)) {
                    if (registerPluginTable.containsKey(plugin)) {
                        return false;
                    }
                }
                registerPluginTable.get(pluginName).shutdown();
                registerPluginTable.remove(pluginName);
            }
            return true;
        }
    }

    public static <T extends CommonPlugin> T getPlugin(String pluginName,Class<T> plugin){
        if(registerPluginTable.containsKey(pluginName)){
            return (T)registerPluginTable.get(pluginName).getPlugin();
        }
        return null;
    }

    public static CommonPlugin getPlugin(String pluginName){
        if(registerPluginTable.containsKey(pluginName)){
            return registerPluginTable.get(pluginName).getPlugin();
        }
        return null;
    }

}
