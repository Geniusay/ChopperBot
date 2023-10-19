package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.Account;
import org.example.pojo.AccountType;
import org.example.sql.annotation.SQLInit;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description
 * @Author welsir
 * @Date 2023/9/23 14:15
 */
public interface AccountMapper extends BaseMapper<Account> {

    @Select("select * from account a join platform pf on a.platformId=pf.id where pf.id = #{id}")
    List<Account> selectUserByPlatform(int id);

    @Select("select * from account_type where uid=#{id}")
    List<AccountType> selectTypeByUid(Long id);

}
