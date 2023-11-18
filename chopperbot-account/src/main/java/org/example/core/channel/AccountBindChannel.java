package org.example.core.channel;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
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

    public boolean delChannel(String name){
        try {
            channelMapper.delete(new QueryWrapper<Channel>().eq("name",name));
        }catch (RuntimeException e){
            log.debug("删除管道失败!");
            return false;
        }
        return true;
    }

    public List<Channel> getChannelList(){
        List<Channel> channelList = channelMapper.selectList(null);
        for (Channel channel : channelList) {
            channelRoute.put(channel.getName(),channel.getRoute());
        }
        return channelMapper.selectList(null);
    }

    public List<Account> getAccountList(String name){
        List<Channel> channels = channelMapper.selectList(new QueryWrapper<Channel>().eq("name", name));
        Long channelId = channels.get(0).getId();
        return channelMapper.getChannelAccounts(channelId);
    }

    public void bindChannel(String channelId,String accountId){
        try {
            //todo: 需测试
            List<Account> accountList = channelAccount.get(channelId);
            accountList.add(accountMapper.selectById(accountId));
            AccountChannel accountChannel = new AccountChannel();
            accountChannel.setAccountId(accountId);
            accountChannel.setChannelId(channelId);
            channelMapper.bindChannel(accountChannel);
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
        sqlInitHelper.initTable("channel","CREATE TABLE `account_channel`  (\n" +
                "  `id` bigint NOT NULL,\n" +
                "  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,\n" +
                "  `route` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,\n" +
                "  PRIMARY KEY (`id`) USING BTREE,\n" +
                "  UNIQUE INDEX `name`(`name` ASC) USING BTREE\n" +
                ")"+
                "INSERT INTO `account_channel` (`id`, `name`, `type`) VALUES (1, 'default', '*.*.*');");

        sqlInitHelper.initTable("account_channel","CREATE TABLE `channel`  (\n" +
                "  `id` bigint NOT NULL,\n" +
                "  `channel_id` bigint NULL DEFAULT NULL,\n" +
                "  `account_id` bigint NULL DEFAULT NULL,\n" +
                "  PRIMARY KEY (`id`) USING BTREE\n" +
                ")");

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
