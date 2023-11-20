package org.example.api;

import org.example.bean.section.PackageSection;
import org.example.constpool.PluginName;
import org.example.core.guard.VideoPushGuard;
import org.example.init.InitPluginRegister;
import org.example.plugin.CommonPlugin;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 * @Description 视频推送插件api
 * @Author welsir
 * @Date 2023/9/5 22:36
 */
@Component
public class VideoPushApi {

    @Resource
    VideoPushGuard videoPushGuard;

    public void pushVideo(PackageSection obj){
        videoPushGuard.sendVideo(obj);
    }

}
