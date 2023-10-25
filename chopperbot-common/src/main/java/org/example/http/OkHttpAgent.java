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
        OkHttpClient.Builder builder;
        if(httpProxy.isEnable()){
            Proxy proxy = httpProxy.httpProxy();
            builder = new OkHttpClient.Builder().proxy(proxy);
        }else{
            builder =  new OkHttpClient.Builder();
        }
        return builder.connectTimeout(120,TimeUnit.SECONDS)
                .readTimeout(60,TimeUnit.SECONDS)
                .writeTimeout(60,TimeUnit.SECONDS)
                .build();
    }
}
