package org.example.init;

/**
 * @author Genius
 * @date 2023/07/29 15:21
 **/

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.bean.Plugin;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 插件注册中心
 */
public class InitPluginRegister {

    public static ConcurrentHashMap<String, Plugin> allPlugins = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<String,Class<? extends InitMachine>> registerPluginTable = new ConcurrentHashMap<>();

    public static boolean isRegister(String pluginName){
        return registerPluginTable.containsKey(pluginName);
    }


}
