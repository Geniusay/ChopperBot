package org.example.controller;

import org.apache.ibatis.annotations.Delete;
import org.example.service.AccountChannelService;
import org.example.service.AccountService;
import org.example.service.impl.AccountChannelServiceImpl;
import org.example.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Description
 * @Author welsir
 * @Date 2023/11/20 11:04
 */
@RestController
@RequestMapping("/channel")
public class AccountChannelApi {

    @Resource
    AccountChannelService channelService;

    @PostMapping("/{name}/{route}")
    public Result addChannel(@PathVariable String name, @PathVariable String route){
       return Result.success(channelService.channelApi().addChannel(name,route));
    }

    @DeleteMapping("/{channelId}")
    public Result delChannel(@PathVariable String channelId){
        return Result.success(channelService.channelApi().delChannel(channelId));
    }

    @GetMapping("/")
    public Result getChannelList(){
        return Result.success(channelService.channelApi().getChannels());
    }

    @GetMapping("/accounts/{channelId}")
    public Result getChannelAccounts(@PathVariable String channelId){
        return Result.success(channelService.channelApi().getChannelAccounts(channelId));
    }

}
