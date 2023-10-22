package org.example.service;

import org.example.api.SystemFileApi;
import org.example.pojo.vo.ConfigVO;
import org.example.util.Result;

import java.util.List;

/**
 * @author Genius
 * @date 2023/04/26 00:59
 **/

public interface FileService {

    //获取所有模块
    List<String> getAllModule();

    //获取所有文件
    List<ConfigVO> getAllConfigs();

    SystemFileApi systemFileApi();

}
