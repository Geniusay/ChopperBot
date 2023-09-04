package org.example.plugin;

import org.example.constpool.PluginName;
import org.example.init.InitPluginRegister;

import java.util.function.Consumer;

/**
 * @author Genius
 * @date 2023/08/17 23:31
 **/
public class PluginCheckAndDo {


    /**
     *   且条件，检测所需的所有插件是否存在，存在则调用success，不存在则调用fail
     */

    public static void CheckAndDo(
            PluginAction success,
            String needPlugin){
        if (!InitPluginRegister.isRegister(needPlugin)) {
            return;
        }
        success.action((InitPluginRegister.getPlugin(needPlugin)));
    }


    public static void CheckAndDo(
            PluginAction success,
            PluginFailAction fail,
            String needPlugin){
        if (!InitPluginRegister.isRegister(needPlugin)) {
            fail.action();
            return;
        }
        success.action((InitPluginRegister.getPlugin(needPlugin)));
    }
}
