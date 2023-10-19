package org.example.init;

/**
 * @author Genius
 * @date 2023/07/29 15:21
 **/

import org.example.constpool.ConstPool;
import org.example.exception.plugin.PluginDependOnException;
import org.example.log.ChopperLogFactory;
import org.example.log.LoggerType;
import org.example.plugin.CommonPlugin;
import org.example.plugin.annotation.CheckPlugin;
import org.example.plugin.annotation.Plugin;
import org.example.thread.ChopperBotGuardPool;
import org.example.util.ClassUtil;
import org.example.util.ExceptionUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 插件注册中心
 */
public class InitPluginRegister {


    public static ConcurrentHashMap<String,List<String>> modulePlugin = new ConcurrentHashMap<>();

    public static ConcurrentHashMap<String, List<String>> fatherAndSonPlugin = new ConcurrentHashMap<>();

    public static ConcurrentHashMap<String,List<String>> moduleFatherAndSonPlugin = new ConcurrentHashMap<>();

    public static ConcurrentHashMap<String, CommonInitMachine> allPlugins = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<String,CommonInitMachine> registerPluginTable = new ConcurrentHashMap<>();

    public static Map<String,Boolean> pluginStartSetting;


    public static boolean initPluginRegister(ApplicationContext ctx){
        Set<Class<?>> initMachineClasses = ClassUtil.getAnnotationClass(ConstPool.PROJECT_PATH + ".init", Plugin.class);
        for (Class<?> initMachineClass : initMachineClasses) {
            try {
                Plugin ano = initMachineClass.getAnnotation(Plugin.class);
                String moduleName = ano.moduleName();
                String pluginName = ano.pluginName();
                String pluginName_CN = ano.pluginName_CN();
                String pluginDescription = ano.pluginDescription();
                List<String> needPlugins = List.of(ano.needPlugin());
                boolean autoStart = pluginStartSetting.containsKey(pluginName)?pluginStartSetting.get(pluginName):ano.autoStart();
                CommonInitMachine initMachine;
                if(ano.springBootPlugin()){
                   initMachine = (CommonInitMachine) ctx.getBean(initMachineClass);
                }else{
                   initMachine = (CommonInitMachine) initMachineClass
                            .getDeclaredConstructor(List.class,boolean.class,String.class,String.class,Class.class)
                            .newInstance(needPlugins,autoStart,moduleName,pluginName,ano.pluginClass());
                }
                initMachine.setIgnore(ano.ignore());
                initMachine.setPluginName_CN(pluginName_CN);
                initMachine.setPluginDescription(pluginDescription);
                pluginStartSetting.put(pluginName,autoStart);
                //全部插件
                if(allPlugins.containsKey(pluginName))return false;
                allPlugins.put(pluginName,initMachine);

                //模块下的所有插件
                if(modulePlugin.containsKey(moduleName)){
                    modulePlugin.get(moduleName).add(pluginName);
                }else{
                    ArrayList<String> list = new ArrayList<>();
                    list.add(pluginName);
                    modulePlugin.put(moduleName,list);
                }

                //所有模块的插件依赖
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
                throw new RuntimeException(e);
            }
        }
        ChopperBotGuardPool.setPluginNum(allPlugins.size());
        //完善 模块下插件的依赖和一次插件初始化检测
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

            AtomicBoolean res = new AtomicBoolean(true);
            InitPluginRegister.allPlugins.forEach(
                    (k,v)->{
                        if(!InitPluginRegister.needStart(k)){
                            List<String> list = fatherAndSonPlugin.get(k);
                            if(list!=null){
                                for (String sonPlugin : list) {
                                    if(InitPluginRegister.needStart(sonPlugin)){
                                        ChopperLogFactory.getLogger(LoggerType.System)
                                                .error("🙃ChopperBot Plugin Error!The [{}] plugin requires the [{}] plugin, " +
                                                        "but the [{}] plugin is not set to start automatically. " +
                                                        "Please go to the ./config/chopperBotConfig.json to start the [{}] plugin !",sonPlugin,k,k,k);
                                        res.set(false);
                                        break;
                                    }
                                }
                            }
                        }
                    }
            );
            return res.get();
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    public static boolean startPlugin(String pluginName){
        if(allPlugins.containsKey(pluginName)){
            if(!registerPluginTable.containsKey(pluginName)){
                CommonInitMachine initMachine = allPlugins.get(pluginName);
                for (String needPlugin : initMachine.getNeedPlugins()) {
                    if(!InitPluginRegister.isRegister(needPlugin)){
                        throw PluginDependOnException.MissingFatherPlugin(needPlugin,pluginName);
                    }
                }
                try {
                    if (initMachine.init()) {
                        registerPluginTable.put(pluginName,initMachine);
                        initMachine.afterInit();
                        return true;
                    }else{
                        return false;
                    }
                }catch (Exception e){
                    ChopperLogFactory.getLogger(LoggerType.System)
                            .error(String.format("❌ %s plugin start failed ! error:%s", pluginName, ExceptionUtil.getCause(e)));
                }
            }else{
                return false;
            }
        }
        return false;
    }


    public static boolean isRegister(String pluginName){
        return registerPluginTable.containsKey(pluginName);
    }

    public static boolean register(CommonInitMachine initMachine){
        if(!registerPluginTable.containsKey(initMachine.getPluginName())){
            registerPluginTable.put(initMachine.getPluginName(),initMachine);
            return true;
        }
        return false;
    }

    public static boolean closePlugin(String pluginName){
        synchronized (pluginName) {
            if (registerPluginTable.containsKey(pluginName)) {
                for (String plugin : fatherAndSonPlugin.get(pluginName)) {
                    if (registerPluginTable.containsKey(plugin)) {
                        throw PluginDependOnException.DependOnPlugin(pluginName,plugin);

                    }
                }
                registerPluginTable.get(pluginName).shutdown();
                registerPluginTable.remove(pluginName);
            }else{
                return false;
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

    public static boolean needStart(String pluginName){
        return pluginStartSetting.getOrDefault(pluginName, false);
    }

}
