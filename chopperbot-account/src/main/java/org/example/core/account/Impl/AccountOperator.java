package org.example.core.account.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.core.account.AccountOperateCenter;
import org.example.core.constpool.ConstPool;
import org.example.core.factory.PlatformFactory;
import org.example.core.factory.PlatformOperation;

import org.example.mapper.AccountMapper;
import org.example.mapper.AccountTypeMapper;
import org.example.core.pojo.Account;
import org.example.core.pojo.AccountType;
import org.example.core.pojo.AccountVO;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author welsir
 * @Date 2023/9/23 14:04
 */
@Service
public class AccountOperator implements AccountOperateCenter {

    @Resource
    private AccountMapper accountMapper;
    @Resource
    AccountTypeMapper accountTypeMapper;
    @Override
    public void insertAccount(int platformId,String username,String password) {
        PlatformOperation platformOperation = PlatformFactory.createPlatformOperation(platformId);
        platformOperation.login(platformId,username,password);
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
                accountVO.setPassword(account.getPassword());
                accountVO.setPlatform(ConstPool.AccountPlatForm.fromId(account.getPlatformId()));
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
