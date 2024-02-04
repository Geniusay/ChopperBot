package org.example.service.impl;

import org.example.api.ChannelApi;
import org.example.service.AccountChannelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description
 * @Author welsir
 * @Date 2023/11/20 11:07
 */
@Service
public class AccountChannelServiceImpl implements AccountChannelService {

    @Resource
    ChannelApi channelApi;

    @Override
    public ChannelApi channelApi() {
        return channelApi;
    }
}
