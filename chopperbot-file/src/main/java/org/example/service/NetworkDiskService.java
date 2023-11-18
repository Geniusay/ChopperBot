package org.example.service;

import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author welsir
 * @Date 2023/11/9 17:55
 */
@Service
public interface NetworkDiskService {

    public String getToken(String code,String clientId,String clientSecret,String redirectUri);

}
