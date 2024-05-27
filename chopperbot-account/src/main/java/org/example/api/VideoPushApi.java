package org.example.api;

import org.example.bean.section.PackageSection;
import org.example.core.guard.VideoPushChannelGuard;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description 视频推送插件api
 * @Author welsir
 * @Date 2023/9/5 22:36
 */
@RestController
@RequestMapping("/video")
public class VideoPushApi {

    @Resource
    VideoPushChannelGuard videoPushGuard;

    @RequestMapping(method = RequestMethod.POST,value = "/push")
    public void pushVideo(PackageSection obj){
        videoPushGuard.sendVideo(obj);
    }

}
