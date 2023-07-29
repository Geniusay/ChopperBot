package org.example.init;

/**
 * @author Genius
 * @date 2023/07/29 15:21
 **/

import java.util.concurrent.ConcurrentHashMap;

/**
 * 插件注册中心
 */
public class InitPluginRegister {

    public static ConcurrentHashMap<String,Class<? extends InitMachine>> registerPluginTable = new ConcurrentHashMap<>();

    public static boolean isRegister(String pluginName){
        return registerPluginTable.containsKey(pluginName);
    }

}
