package org.example.http;

import okhttp3.OkHttpClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;

/**
 * @author Genius
 * @date 2023/10/23 14:24
 **/
@Component
public class OkHttpAgent implements ProxyAgent<OkHttpClient>{

    @Resource
    HttpProxy httpProxy;

    @Override
    public OkHttpClient agentClient() {
        if(httpProxy.isEnable()){
            Proxy proxy = httpProxy.httpProxy();
            return new OkHttpClient.Builder().proxy(proxy).connectTimeout(60, TimeUnit.SECONDS).build();
        }else{
            return new OkHttpClient.Builder().build();
        }
    }
}
