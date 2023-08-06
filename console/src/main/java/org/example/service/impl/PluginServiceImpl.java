package org.example.service.impl;

import org.example.bean.Plugin;
import org.example.init.CommonInitMachine;
import org.example.init.InitPluginRegister;
import org.example.service.PluginService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Genius
 * @date 2023/08/02 18:28
 **/
@Service
public class PluginServiceImpl implements PluginService {

    @Override
    public List<Plugin> getPlugins(String moduleName) {
        List<Plugin> list = new ArrayList<>();
        Map<String,CommonInitMachine> commonInitMachineMap;
        if(moduleName==null){
            commonInitMachineMap = InitPluginRegister.allPlugins;
        }else{
            commonInitMachineMap = new HashMap<>();
            for (String s : InitPluginRegister.modulePlugin.get(moduleName)) {
                commonInitMachineMap.put(s,InitPluginRegister.allPlugins.get(s));
            }
        }

        commonInitMachineMap.forEach(
                (k,v)->{
                    list.add(packToPlugin(v));
                }
        );

        return list;
    }


    private Plugin packToPlugin(CommonInitMachine commonInitMachine){
        String moduleName = commonInitMachine.getModuleName();
        String pluginName = commonInitMachine.getPluginName();
        boolean autoStart = commonInitMachine.isAutoStart();
        boolean register = InitPluginRegister.isRegister(pluginName);
        List<String> needPlugins = commonInitMachine.getNeedPlugins();
        String pluginName_cn = commonInitMachine.getPluginName_CN();
        String pluginDescription = commonInitMachine.getPluginDescription();
        return new Plugin(pluginName,moduleName,pluginName_cn,pluginDescription,needPlugins,autoStart,register);
    }
}
