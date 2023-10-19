package org.example.init.moudle;

import org.example.constpool.ModuleName;
import org.example.init.ModuleInitMachine;
import org.example.log.ChopperLogFactory;
import org.example.log.LoggerType;
import org.example.mapper.AccountMapper;
import org.example.mapper.AccountTypeMapper;
import org.example.plugin.annotation.Module;
import org.example.sql.SQLInitHelper;
import org.example.sql.annotation.SQLInit;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>用于初始化弹幕模块所需</p>
 *
 * @author welsir
 * @date 2023/7/31 10:11
 */
@Module
public class AccountModuleInitMachine extends ModuleInitMachine{

    public AccountModuleInitMachine() {
        super(List.of(),
                ChopperLogFactory.getLogger(LoggerType.Account),
                ModuleName.ACCOUNT);
    }

}
