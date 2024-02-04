package org.example.service.impl;

import org.example.api.VideoPublishApi;
import org.example.api.VideoPushApi;
import org.example.service.VideoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description
 * @Author welsir
 * @Date 2023/11/20 17:40
 */
@Service
public class VideoPushServiceImpl implements VideoService {

    @Resource
    VideoPushApi videoPushApi;

    @Override
    public VideoPushApi videoApi() {
        return videoPushApi;
    }
}
