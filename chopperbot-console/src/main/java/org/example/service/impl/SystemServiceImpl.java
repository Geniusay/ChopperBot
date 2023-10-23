package org.example.service.impl;

import org.example.http.HttpProxy;
import org.example.service.SystemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Genius
 * @date 2023/10/23 14:42
 **/
@Service
public class SystemServiceImpl implements SystemService {

    @Resource
    HttpProxy httpProxy;

    @Override
    public boolean changeProxy(String address, int port) {
        httpProxy.setAddress(address);
        httpProxy.setPort(port);
        return true;
    }
}
