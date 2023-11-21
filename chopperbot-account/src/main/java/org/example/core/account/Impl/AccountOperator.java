package org.example.core.account.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.core.account.AccountOperateCenter;
import org.example.core.constpool.ConstPool;
import org.example.core.factory.PlatformFactory;
import org.example.core.factory.PlatformOperation;

import org.example.mapper.AccountMapper;
import org.example.mapper.AccountTypeMapper;
import org.example.pojo.Account;
import org.example.pojo.AccountType;
import org.example.pojo.vo.AccountVO;
import org.openqa.selenium.Cookie;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author welsir
 * @Date 2023/9/23 14:04
 */
@Service
public class AccountOperator extends ServiceImpl<AccountMapper,Account> implements AccountOperateCenter {

    @Resource
    AccountMapper accountMapper;
    @Resource
    AccountTypeMapper accountTypeMapper;
    @Override
    public void insertAccount(int platformId,String username) {
        PlatformOperation platformOperation = PlatformFactory.createPlatformOperation(platformId);
        Set<Cookie> cookies = platformOperation.login(platformId, username);
        List<StringBuilder> list = new ArrayList<>();
        for (Cookie cookie : cookies) {
            StringBuilder sb = new StringBuilder();
            sb.append("name="+cookie.getName()+",");
            sb.append("value="+cookie.getValue()+",");
            sb.append("domain="+cookie.getDomain()+",");
            sb.append("path="+cookie.getPath()+",");
            sb.append("isSecure="+cookie.isSecure()+",");
            sb.append("isHttpOnly="+cookie.isHttpOnly()+",");
            sb.append("sameSite="+cookie.getSameSite());
            list.add(sb);
        }
        String realCookies = list.toString();
        Account account = new Account();
        account.setPlatform_id(platformId);
        account.setCookies(realCookies);
        account.setUsername(username);
        System.out.println(account);
        accountMapper.insert(account);
    }

    @Override
    public List<AccountVO> getAllUsers() {
        List<Account> accountList = accountMapper.selectList(null);
        List<AccountType> accountTypes = accountTypeMapper.selectList(null);
        return addTypeToAccount(accountTypes,accountList);
    }

    @Override
    public List<AccountVO> getAllUsers(int id) {
        List<Account> accountList = accountMapper.selectUserByPlatform(id);
        List<AccountType> accountTypes = accountTypeMapper.selectList(null);
        return addTypeToAccount(accountTypes,accountList);
    }

    @Override
    public void editUser(Account account) {
        int i = accountMapper.updateById(account);
        Assert.isTrue(i==1,"Update user fail!Please try again or check the wrong!");
    }

    public List<AccountVO> addTypeToAccount(List<AccountType> accountTypes,List<Account> accountList){
        Map<Long, List<AccountType>> typeMap = accountTypes.stream()
                .collect(Collectors.groupingBy(AccountType::getUid));
        List<AccountVO> accountVOList = new ArrayList<>();
        for (Account account : accountList) {
            AccountVO accountVO = new AccountVO();
            List<AccountType> types = typeMap.get(account.getId());
            if (types != null) {
                accountVO.setTypeList(types);
                accountVO.setUid(account.getId());
                accountVO.setUsername(account.getUsername());
                accountVO.setPlatform(ConstPool.AccountPlatForm.fromId(account.getPlatform_id()));
                accountVOList.add(accountVO);
            }
        }
        return accountVOList;
    }

    @Override
    public void deleteAccount(int uid){
        accountMapper.deleteById(uid);
        QueryWrapper<AccountType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid",uid);
        accountTypeMapper.delete(queryWrapper);
    }
}
