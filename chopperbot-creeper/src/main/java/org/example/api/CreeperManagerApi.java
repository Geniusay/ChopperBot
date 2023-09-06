package org.example.api;

import org.example.constpool.PluginName;
import org.example.core.manager.CreeperBean;
import org.example.core.manager.CreeperManager;
import org.example.init.InitPluginRegister;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Genius
 * @date 2023/09/05 23:52
 **/
@Component
public class CreeperManagerApi {



    public  List<CreeperBean> getAllCreeper(){
        CreeperManager plugin = InitPluginRegister.getPlugin(PluginName.CREEPER_MANAGER_PLUGIN, CreeperManager.class);
        assert plugin != null;
        return plugin.getCreeperBeans();
    }
}
