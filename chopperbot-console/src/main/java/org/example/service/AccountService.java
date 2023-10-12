package org.example.service;



import org.example.core.pojo.Account;
import org.example.core.pojo.AccountVO;

import java.util.List;

/**
 * @Description
 * @Author welsir
 * @Date 2023/10/12 22:26
 */
public interface AccountService {

    List<AccountVO> getAllUser(int id);

    List<AccountVO> getAllUser();

    void login(int platformId,String username,String password);

    void edit(Account account);

}
