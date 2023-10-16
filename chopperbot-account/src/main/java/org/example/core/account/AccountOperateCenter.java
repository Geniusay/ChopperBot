package org.example.core.account;


import org.example.pojo.Account;
import org.example.pojo.AccountVO;

import java.util.List;

/**
 * @Description
 * @Author welsir
 * @Date 2023/9/23 14:02
 */
public interface AccountOperateCenter {

    void insertAccount(int platformId,String username,String password);

    List<AccountVO> getAllUsers();

    List<AccountVO> getAllUsers(int id);

    void deleteAccount(int uid);

    void editUser(Account account);
}
