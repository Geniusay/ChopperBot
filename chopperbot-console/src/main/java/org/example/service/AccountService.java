package org.example.service;



import org.example.api.AccountApi;
import org.example.api.GPTApi;
import org.example.pojo.Account;
import org.example.pojo.AccountVO;

import java.util.List;

/**
 * @Description
 * @Author welsir
 * @Date 2023/10/12 22:26
 */
public interface AccountService {

    AccountApi accountPlugin();

    GPTApi chatGptPlugin();
}
