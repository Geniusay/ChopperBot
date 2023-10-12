package org.example.core.account.Impl;

import org.example.core.account.AccountCenter;
import org.example.core.factory.PlatformFactory;
import org.example.mapper.AccountMapper;
import org.example.pojo.Account;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @Author welsir
 * @Date 2023/9/23 14:04
 */
@Component
public class AccountOperator implements AccountCenter {

    @Resource
    private AccountMapper accountMapper;
    @Override
    public void insertAccount(int platformId) {
        PlatformFactory.createPlatformOperation(platformId);
    }

    @Override
    public List<Account> getAllUsers(int id) {
        return accountMapper.selectUserByPlatform(id);
    }

    @Override
    public void editUser(Account account) {
        int i = accountMapper.updateById(account);
        Assert.isTrue(i==1,"Update user fail!Please try again or check the wrong!");
    }
}
