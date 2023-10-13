package org.example.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.constpool.ConstPool;
import org.example.exception.InitException;
import org.example.init.CommonInitMachine;
import org.example.init.InitMachine;
import org.example.init.InitPluginRegister;
import org.example.init.ModuleInitMachine;
import org.example.plugin.annotation.Module;
import org.example.plugin.annotation.Plugin;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * plugin工具，用来排序module初始化顺序以及检测是否存在相互依赖
 * @author Genius
 * @date 2023/07/31 17:17
 **/
public class PluginUtil {

    @Data
    @AllArgsConstructor
    static class NeedPlugin{
        private InitMachine initMachine;
        private int needTimes;
    }

    static class NeedTimesComparator implements Comparator<NeedPlugin> {
        @Override
        public int compare(NeedPlugin p1,NeedPlugin p2) {
            return p2.getNeedTimes() - p1.getNeedTimes();
        }
    }

    public static List<CommonInitMachine> getAllModuleInit() throws Exception {
        Set<Class<?>> classes = ClassUtil.getAnnotationClass(ConstPool.PROJECT_PATH + ".init", Module.class);
        List<CommonInitMachine> list = new ArrayList<>();
        for (Class aClass : classes) {
            list.add((CommonInitMachine) aClass.getDeclaredConstructor().newInstance());
        }
        return sortModuleInit(list);
    }

    public static List<CommonInitMachine> getModuleAllPluginInit(String moduleName) throws Exception{
        List<String> list = InitPluginRegister.modulePlugin.get(moduleName);
        List<CommonInitMachine> commonInitMachines = new ArrayList<>();
        for (String name : list) {
            commonInitMachines.add(InitPluginRegister.allPlugins.get(name));
        }
        return sortModuleAllPluginInit(commonInitMachines);
    }

    private static List<CommonInitMachine> sortModuleAllPluginInit(List<CommonInitMachine> list){
        Map<CommonInitMachine,Integer> needPlugins = new HashMap<>();
        Map<String,CommonInitMachine> NameToInitMachine = new HashMap<>();
        for (CommonInitMachine initMachine : list) {
            String moduleName = initMachine.getPluginName();
            if(!NameToInitMachine.containsKey(moduleName)){
                needPlugins.put(initMachine,0);
                NameToInitMachine.put(moduleName,initMachine);
            }else{
                throw new InitException("Found same module name!");
            }
        }

        for(CommonInitMachine initMachine:list){
            List<String> needPluginsList = InitPluginRegister.moduleFatherAndSonPlugin.get(initMachine.getPluginName());
            needPluginsList.forEach(
                    plugin->{
                        CommonInitMachine obj = NameToInitMachine.get(plugin);
                        needPlugins.put(obj,needPlugins.get(obj)+1);
                    }
            );
        }
        List<CommonInitMachine> machines = new ArrayList<>();
        int n = needPlugins.size();

        while(machines.size()<n){
            AtomicBoolean isLoop = new AtomicBoolean(true);
            needPlugins.forEach(
                    (k,v)->{
                        if(v==0){
                            machines.add(k);
                            InitPluginRegister.moduleFatherAndSonPlugin.get(k.getPluginName()).forEach(
                                    h->{
                                        CommonInitMachine machine = NameToInitMachine.get(h);
                                        needPlugins.put(machine,needPlugins.get(machine)-1);
                                    }
                            );
                            needPlugins.put(k,v-1);
                            isLoop.set(false);
                        }
                    }
            );
            if(isLoop.get()){
                throw new InitException("Found loop depend on");
            }
        }
        return machines;
    }

    private static List<CommonInitMachine> sortModuleInit(List<CommonInitMachine> list){
        Map<CommonInitMachine,Integer> needPlugins = new HashMap<>();
        Map<String,CommonInitMachine> NameToInitMachine = new HashMap<>();
        for (CommonInitMachine initMachine : list) {
            String moduleName = ((ModuleInitMachine) initMachine).getModuleName();
            if(!NameToInitMachine.containsKey(moduleName)){
                needPlugins.put(initMachine,0);
                NameToInitMachine.put(moduleName,initMachine);
            }else{
                throw new InitException("Found same module name!");
            }
        }

        for(CommonInitMachine initMachine:list){
            List<String> needPluginsList = ((ModuleInitMachine) initMachine).getNeedPlugins();
            needPluginsList.forEach(
                    plugin->{
                        CommonInitMachine obj = NameToInitMachine.get(plugin);
                        needPlugins.put(obj,needPlugins.get(obj)+1);
                    }
            );
        }
        List<CommonInitMachine> machines = new ArrayList<>();
        int n = needPlugins.size();

        while(machines.size()<n){
            AtomicBoolean isLoop = new AtomicBoolean(true);
            needPlugins.forEach(
                    (k,v)->{
                        if(v==0){
                            machines.add(k);
                            ((ModuleInitMachine)k).getNeedPlugins().forEach(
                                    h->{
                                        CommonInitMachine machine = NameToInitMachine.get(h);
                                        needPlugins.put(machine,needPlugins.get(machine)-1);
                                    }
                            );
                            needPlugins.put(k,v-1);
                            isLoop.set(false);
                        }
                    }
            );
            if(isLoop.get()){
                throw new InitException("Found loop depend on");
            }
        }
        Collections.reverse(machines);
        return machines;
    }
}
