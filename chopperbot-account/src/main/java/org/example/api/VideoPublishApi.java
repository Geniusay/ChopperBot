package org.example.api;

import org.example.constpool.PluginName;
import org.example.core.guard.VideoPushGuard;
import org.example.init.InitPluginRegister;
import org.example.plugin.CommonPlugin;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @Description 视频推送插件api
 * @Author welsir
 * @Date 2023/9/5 22:36
 */
@Component
public class VideoPublishApi {

    public static void pushVideo(Object obj){
        VideoPushGuard plugin = InitPluginRegister.getPlugin(PluginName.VIDEO_PUSH_PLUGIN, VideoPushGuard.class);
        Assert.isNull(plugin,"plugin is null!");
        plugin.sendVideo(obj);
    }

}
