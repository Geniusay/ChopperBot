package org.example.core.account.Impl;

import org.example.core.account.AccountCenter;
import org.example.core.factory.PlatformFactory;
import org.example.core.mapper.AccountMapper;
import org.example.pojo.Account;
import org.springframework.stereotype.Component;

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
        return accountMapper.getUserByPlatform(id);
    }
}
