package org.example.api;

import org.example.constpool.PluginName;
import org.example.core.guard.VideoPushGuard;
import org.example.init.InitPluginRegister;
import org.example.plugin.CommonPlugin;

/**
 * @Description 视频推送插件api
 * @Author welsir
 * @Date 2023/9/5 22:36
 */
public class VideoPublishApi {

    public static void pushVideo(Object obj){
        VideoPushGuard plugin = InitPluginRegister.getPlugin(PluginName.VIDEO_PUSH_PLUGIN, VideoPushGuard.class);
        if (plugin != null) {
            plugin.sendVideo(obj);
        }else {
            System.out.println("plugin is null!");
        }
    }



    public static void main(String[] args) {
        pushVideo("hello");
    }

}
