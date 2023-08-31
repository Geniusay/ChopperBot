package org.example.init;

/**
 * @author Genius
 * @date 2023/07/21 00:18
 **/

import org.example.config.HotModuleConfig;
import org.example.constpool.ConstPool;
import org.example.constpool.ModuleName;
import org.example.constpool.PluginName;
import org.example.plugin.CommonPlugin;
import org.example.plugin.annotation.Plugin;

import java.util.List;

/**
 * 热门模块配置文件初始化机器
 */
@Plugin(moduleName = ModuleName.HOT,
        pluginName = PluginName.HOT_CONFIG_PLUGIN,
        pluginName_CN = "热门模块配置文件",
        needPlugin = {PluginName.FILE_CACHE_PLUGIN},
        pluginClass= HotModuleConfig.class )
public class HotConfigInitMachine extends ConfigInitMachine {


    public HotConfigInitMachine(List<String> needPlugins, boolean isAutoStart, String moduleName, String name, Class<? extends CommonPlugin> clazz) {
        super(needPlugins, isAutoStart, moduleName, name, clazz);
    }

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        while (true){
            clearConsole(2);
            System.out.printf("A times:%s\n", i);
            System.out.printf("B times:%s\n", i);
            Thread.sleep(1000);
            i++;
        }
    }

    private static void clearConsole(int lines) {
        for (int i = 0; i < lines; i++) {
            System.out.print("\033[2K"); // 清除当前行
            System.out.print("\033[1A"); // 上移一行
        }
        System.out.print("\033[2K"); // 清除最后一行
    }
}
