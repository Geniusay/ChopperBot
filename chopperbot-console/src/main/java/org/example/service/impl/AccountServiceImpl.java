package org.example.service.impl;

import org.example.api.AccountApi;

import org.example.api.GPTApi;
import org.example.pojo.Account;

import org.example.pojo.AccountVO;
import org.example.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @Author welsir
 * @Date 2023/10/12 22:39
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Resource
    AccountApi accountApi;

    @Resource
    GPTApi gptApi;
    @Override
    public List<AccountVO> getAllUser(int id) {
        return accountApi.getAllUsers(id);
    }

    @Override
    public List<AccountVO> getAllUser() {
        return accountApi.getAllUsers();
    }

    @Override
    public void login(int platformId,String username, String password) {
        accountApi.addAccountSaveCookie(platformId,username,password);
    }

    @Override
    public void edit(Account account) {
        accountApi.editUser(account);
    }

    @Override
    public GPTApi chatGptPlugin() {
        return gptApi;
    }
}
