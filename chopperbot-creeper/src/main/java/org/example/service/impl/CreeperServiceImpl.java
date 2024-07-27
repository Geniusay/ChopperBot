package org.example.service.impl;

import org.example.api.CreeperManagerApi;
import org.example.api.MonitorCenterApi;
import org.example.api.TaskCenterApi;
import org.example.service.CreeperService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Genius
 * @date 2023/09/06 00:56
 **/
@Service
public class CreeperServiceImpl implements CreeperService {

    @Resource
    TaskCenterApi taskCenterApi;

    @Resource
    CreeperManagerApi creeperManagerApi;

    @Resource
    MonitorCenterApi monitorCenterApi;

    @Override
    public CreeperManagerApi creeperManagerApi() {
        return creeperManagerApi;
    }

    @Override
    public TaskCenterApi taskCenterApi() {
        return taskCenterApi;
    }

    @Override
    public MonitorCenterApi monitorApi() {
        return monitorCenterApi;
    }

}
