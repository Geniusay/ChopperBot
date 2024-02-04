package org.example.api;

import org.example.service.NetworkDiskService;
import org.example.util.Result;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Description
 * @Author welsir
 * @Date 2023/11/9 17:46
 */
@Component
public class NetworkDiskApi {

    @Resource
    NetworkDiskService networkDiskService;

    public Result getToken(String code, String clientId, String clientSecret, String redirectUri){
        return Result.success(networkDiskService.getToken(code,clientId,clientSecret,redirectUri));
    }

}
