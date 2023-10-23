package org.example.service.impl;

import org.example.api.AccountApi;

import org.example.api.OpenAPIPluginApi;

import org.example.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description
 * @Author welsir
 * @Date 2023/10/12 22:39
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Resource
    AccountApi accountApi;

    @Resource
    OpenAPIPluginApi openAPIPluginApi;

    @Override
    public AccountApi accountPlugin() {
        return accountApi;
    }

    @Override
    public OpenAPIPluginApi chatGptPlugin() {
        return openAPIPluginApi;
    }
}
