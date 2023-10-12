package org.example.core.account;


import org.example.core.pojo.Account;
import org.example.core.pojo.AccountVO;

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
