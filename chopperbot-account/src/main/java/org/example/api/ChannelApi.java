package org.example.api;


import org.example.core.channel.AccountBindChannel;
import org.example.util.Result;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Description
 * @Author welsir
 * @Date 2023/11/17 8:34
 */
@Component
public class ChannelApi {

    @Resource
    AccountBindChannel accountChannel;

    public Result addChannel(String name,String route){
        return Result.success(accountChannel.addChannel(name,route));
    }

    public Result delChannel(String channelId){
        return Result.success(accountChannel.delChannel(channelId));
    }

    public Result getChannelAccounts(String channelId){
        return Result.success(accountChannel.getAccountList(channelId));
    }

    public Result getChannels(){
        return Result.success(accountChannel.getChannelList());
    }

}
