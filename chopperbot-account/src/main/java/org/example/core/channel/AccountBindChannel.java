package org.example.core.channel;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.AccountChannelMapper;
import org.example.mapper.AccountMapper;
import org.example.mapper.ChannelMapper;
import org.example.plugin.SpringBootPlugin;
import org.example.pojo.Account;
import org.example.pojo.AccountChannel;
import org.example.pojo.Channel;
import org.example.sql.SQLInitHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Description 账号通道类
 * @Author welsir
 * @Date 2023/11/17 8:18
 */
@Component
@Slf4j
public class AccountBindChannel extends SpringBootPlugin {

    private HashMap<String,List<Account>> channelAccount;
    private HashMap<String,String> channelRoute;

    @Resource
    SQLInitHelper sqlInitHelper;
    @Resource
    ChannelMapper channelMapper;
    @Resource
    AccountMapper accountMapper;

    public boolean addChannel(String name,String route){
        try {
            Channel channel = new Channel();
            channel.setName(name);
            channel.setRoute(route);
            channelMapper.insert(channel);
        }catch (RuntimeException e){
            log.debug("管道名称不能重复!");
            return false;
        }
        return true;
    }

    public boolean delChannel(String channelId){
        try {
            channelMapper.deleteById(channelId);
        }catch (RuntimeException e){
            log.debug("删除管道失败!");
            return false;
        }
        return true;
    }

    public List<Channel> getChannelList(){
        List<Channel> channelList = channelMapper.selectList(null);
        for (Channel channel : channelList) {
            channelRoute.putIfAbsent(channel.getId().toString(),channel.getRoute());
        }
        return channelList;
    }

    public List<Account> getAccountList(String channelId){
        Channel channel = channelMapper.selectById(channelId);
        Long id = channel.getId();
        return channelMapper.getChannelAccounts(id);
    }

    public void bindChannel (String channelId,String accountId){
        try {
            AccountChannel accountChannel = new AccountChannel();
            accountChannel.setAccountId(accountId);
            accountChannel.setChannelId(channelId);
            channelMapper.bindChannel(accountChannel);
            channelAccount.putIfAbsent(channelId,new ArrayList<>());
            List<Account> accountList = channelAccount.get(channelId);
            accountList.add(accountMapper.selectById(accountId));
        }catch (RuntimeException e){
            log.debug("账号绑定失败:"+e);
        }
    }

    public void unbindChannel(String channelId,String accountId){
        try {
            List<Account> accountList = channelAccount.get(channelId);
            Account account = accountMapper.selectById(accountId);
            accountList.remove(account);
            channelMapper.unBindChannel(accountId,channelId);
        }catch (RuntimeException e){
            log.debug("账号取消绑定失败:"+e);
        }
    }


    public Map<String,List<Account>> getChannelAccount(){
        return channelAccount;
    }

    public Map<String,String> getChannelRoute(){
        return  channelRoute;
    }

    @Override
    public boolean init() {
        channelAccount = new HashMap<>();
        channelRoute = new HashMap<>();
        sqlInitHelper.initTable("channel","CREATE TABLE `channel`  (\n" +
                "  `id` INTEGER NOT NULL,\n" +
                "  `name` TEXT NOT NULL,\n" +
                "  `route` TEXT NOT NULL,\n" +
                "  PRIMARY KEY (id),\n" +
                "  UNIQUE (name)\n" +
                ")");
        ArrayList<Channel> channels = new ArrayList<>();
        Channel channel = new Channel();
        channel.setRoute("*.*.*");
        channel.setName("default");
        channel.setId(1L);
        channels.add(channel);
        sqlInitHelper.initData(channels,channelMapper);
        sqlInitHelper.initTable("account_channel","CREATE TABLE `account_channel`  (\n" +
                "  `id` INTEGER NOT NULL,\n" +
                "  `channel_id` TEXT ,\n" +
                "  `account_id` TEXT ,\n" +
                "  PRIMARY KEY (id)\n" +
                ")");
        List<Channel> channelList = channelMapper.selectList(null);
        for (Channel channel1 : channelList) {
            channelRoute.put(channel1.getId().toString(),channel1.getRoute());
        }
        List<AccountChannel> accountChannels = channelMapper.queryAccountChannel();
        for (AccountChannel accountChannel : accountChannels) {
            channelAccount.putIfAbsent(accountChannel.getChannelId(),new ArrayList<>());
            List<Account> accountList = channelAccount.get(accountChannel.getChannelId());
            Account account = accountMapper.selectById(accountChannel.getAccountId());
            accountList.add(account);
        }
        return true;
    }

    /*
     * 建表
     */
    @Override
    public List<AccountChannel> sqlInit() {
        return Collections.emptyList();
    }
}
