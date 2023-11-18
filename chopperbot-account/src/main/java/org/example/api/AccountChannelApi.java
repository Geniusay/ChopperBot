package org.example.api;

import org.example.core.channel.AccountChannel;
import org.example.util.Result;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Description
 * @Author welsir
 * @Date 2023/11/17 8:34
 */
@Component
public class AccountChannelApi {

    @Resource
    AccountChannel accountChannel;

    public Result addChannel(String name){
        return Result.success(accountChannel.addChannel(name));
    }

    public Result delChannel(String name){
        return Result.success(accountChannel.delChannel(name));
    }

    public Result getChannelAccounts(String name){

        return Result.success();
    }

    public Result getChannels(){
        return Result.success(accountChannel.getChannelList());
    }

}
