package org.example.api;

import org.example.core.account.Impl.AccountOperator;
import org.example.core.pojo.Account;
import org.example.core.pojo.AccountVO;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @Author welsir
 * @Date 2023/9/22 14:11
 */
@Component
public class AccountApi {

    @Resource
    private AccountOperator accountOperator;

    /*
     * 第一次登陆调用此方法将账号对应cookie存入数据库中
     * 目前仅支持两个平台的账号保管(抖音、b站)
     * 目前只支持以下方式登录
     * 抖音登录默认为验证码登录
     * b站登录默认为账号密码登录
     */
    public void addAccountSaveCookie(int platformId, @RequestBody String username, @RequestBody String password){
        accountOperator.insertAccount(platformId,username,password);
    }

    /*
     * 根据平台id获取用户集合
     */
    public List<AccountVO> getAllUsers(int platformId){
        return accountOperator.getAllUsers(platformId);
    }

    public List<AccountVO> getAllUsers(){
        return accountOperator.getAllUsers();
    }

    /*
     * 修改用户属性
     */
    public void editUser(Account account){
        accountOperator.editUser(account);
    }

    public void deleteUser(int uid){
        accountOperator.deleteAccount(uid);
    }
}
