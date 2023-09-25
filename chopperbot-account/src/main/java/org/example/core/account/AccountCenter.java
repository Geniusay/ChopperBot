package org.example.core.account;

import org.example.pojo.Account;

import java.util.List;

/**
 * @Description
 * @Author welsir
 * @Date 2023/9/23 14:02
 */
public interface AccountCenter {

    void insertAccount(int platformId);

    List<Account> getAllUsers(int id);

}
