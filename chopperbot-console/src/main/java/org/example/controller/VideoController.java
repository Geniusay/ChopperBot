package org.example.controller;

import org.example.bean.section.PackageSection;
import org.example.pojo.VideoToPublish;
import org.example.service.VideoService;
import org.example.util.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description
 * @Author welsir
 * @Date 2023/11/20 17:38
 */
@RestController
@RequestMapping("/video")
public class VideoController {

    @Resource
    VideoService videoService;

    @PostMapping("/")
    public Result pushVideo(@RequestBody PackageSection video){
        videoService.videoApi().pushVideo(video);
        return Result.success();
    }

}
