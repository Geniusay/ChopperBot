package org.example.api;

import org.example.core.account.Impl.AccountOperator;
import org.example.pojo.Account;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @Author welsir
 * @Date 2023/9/22 14:11
 */
@RestController
@RequestMapping("account")
public class AccountApi {

    @Resource
    private AccountOperator accountOperator;

    @PostMapping("/addUser/{uid}")
    public void addAccount(@PathVariable int uid){
        accountOperator.insertAccount(uid);
    }

    @GetMapping("/getUser/{platformId}")
    public List<Account> getAllUsers(@PathVariable int platformId){
        return accountOperator.getAllUsers(platformId);
    }

    @PostMapping("/editUser")
    public void editUser(@RequestBody Account account){
        accountOperator.editUser(account);
    }
}
