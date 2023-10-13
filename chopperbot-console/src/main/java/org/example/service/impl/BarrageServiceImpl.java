package org.example.service.impl;

import org.example.api.BarrageScoreCurvePluginApi;
import org.example.service.BarrageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Genius
 * @date 2023/10/13 17:53
 **/
@Service
public class BarrageServiceImpl implements BarrageService {

    @Resource
    BarrageScoreCurvePluginApi barrageScoreCurvePluginApi;


    @Override
    public BarrageScoreCurvePluginApi barrageScoreCurvePluginApi() {
        return barrageScoreCurvePluginApi;
    }
}
