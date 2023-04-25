package org.example.controller;

import com.genius.assistant.common.Result;
import org.example.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Genius
 * @date 2023/04/26 01:05
 **/

@RestController
@RequestMapping("/config")
public class FileController {

    @Resource
    FileService fileService;

    /**
     * 获取全部配置文件的模块
     */
    @GetMapping("/allConfigModule")
    public Result getAllConfigModule(){
        return Result.success(fileService.getAllModule());
    }

    @GetMapping("/allConfigFiles")
    public Result getAllConfigFiles(){
        return Result.success(fileService.getAllConfigs());
    }
}
