package org.example.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.exception.InitException;
import org.example.init.InitMachine;
import org.example.init.ModuleInitMachine;

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

    public static List<InitMachine> getAllModuleInit() throws Exception {
        List<String> classes = ClassUtil.getClassesInPackage("org.example.init.module");
        List<InitMachine> list = new ArrayList<>();
        for (String aClass : classes) {
            list.add((InitMachine) Class.forName(aClass).getDeclaredConstructor().newInstance());
        }
        return sortModuleInit(list);

    }

    private static List<InitMachine> sortModuleInit(List<InitMachine> list){
        Map<InitMachine,Integer> needPlugins = new HashMap<>();
        Map<String,InitMachine> NameToInitMachine = new HashMap<>();
        for (InitMachine initMachine : list) {
            String moduleName = ((ModuleInitMachine) initMachine).getModuleName();
            if(!NameToInitMachine.containsKey(moduleName)){
                needPlugins.put(initMachine,0);
                NameToInitMachine.put(moduleName,initMachine);
            }else{
                throw new InitException("Found same module name!");
            }
        }

        for(InitMachine initMachine:list){
            List<String> needPluginsList = ((ModuleInitMachine) initMachine).getNeedPlugins();
            needPluginsList.forEach(
                    plugin->{
                        InitMachine obj = NameToInitMachine.get(plugin);
                        needPlugins.put(obj,needPlugins.get(obj)+1);
                    }
            );
        }
        List<InitMachine> machines = new ArrayList<>();
        int n = needPlugins.size();

        while(machines.size()<n){
            AtomicBoolean isLoop = new AtomicBoolean(true);
            needPlugins.forEach(
                    (k,v)->{
                        if(v==0){
                            machines.add(k);
                            ((ModuleInitMachine)k).getNeedPlugins().forEach(
                                    h->{
                                        InitMachine machine = NameToInitMachine.get(h);
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
