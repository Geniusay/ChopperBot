package org.example.service.impl;

import org.example.api.HeatRecommendApi;
import org.example.api.HotModuleApi;
import org.example.api.HotModuleGuardApi;
import org.example.api.LiverFollowApi;
import org.example.bean.Live;
import org.example.bean.HotModule;
import org.example.bean.hotmodule.HotModuleList;
import org.example.core.HotModuleDataCenter;
import org.example.service.HotModuleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Genius
 * @date 2023/07/21 17:23
 **/

@Service
public class HotModuleServiceImpl implements HotModuleService {

    @Resource
    HotModuleApi hotModuleApi;
    @Resource
    LiverFollowApi liverFollowApi;

    @Resource
    HeatRecommendApi heatRecommendApi;

    @Resource
    HotModuleGuardApi hotModuleGuardApi;
    /**
     * 获得热门模块下的热门直播
     * @param moduleId
     * @return
     */

    @Override
    public HotModuleApi hotModuleApi() {
        return hotModuleApi;
    }

    @Override
    public LiverFollowApi liverFollowApi() {
        return liverFollowApi;
    }

    @Override
    public HotModuleGuardApi hotModuleGuardApi() {
        return hotModuleGuardApi;
    }

    @Override
    public HeatRecommendApi heatRecommendApi() {
        return heatRecommendApi;
    }
}
