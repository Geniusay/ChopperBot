package org.example.plugin;

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
            PluginAction fail,
            String...needPlugins){
        for (String needPlugin : needPlugins) {
            if (!InitPluginRegister.isRegister(needPlugin)) {
                fail.action();
                return;
            }
        }
        success.action();

    }

    /**
     *   且条件，检测所需的所有插件是否存在，存在则调用success，不存在则返回
     */
    public static void CheckAndDo(
            PluginAction success,
            String...needPlugins){
        for (String needPlugin : needPlugins) {
            if (!InitPluginRegister.isRegister(needPlugin)) {
                return;
            }
        }
        success.action();
    }

}
