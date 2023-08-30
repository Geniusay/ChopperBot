package org.example.core.guard;


import org.example.constpool.PluginName;
import org.example.init.InitPluginRegister;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Genius
 * @date 2023/07/21 11:39
 **/
public class HotModuleGuardInstance {

    private static volatile HotModuleGuard Instance;

    public static HotModuleGuard getInstance(){
        if(Instance==null){
            synchronized (HotModuleGuardInstance.class){
                if(Instance==null){
                    Instance = InitPluginRegister.getPlugin(PluginName.HOT_GUARD_PLUGIN,HotModuleGuard.class);
                }
            }
        }
        return Instance;
    }


}
