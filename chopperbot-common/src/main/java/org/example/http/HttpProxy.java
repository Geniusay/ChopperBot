package org.example.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 * @author Genius
 * @date 2023/10/23 14:14
 **/
@Data
@Configuration
public class HttpProxy {

    @Value("${chopperbot.proxy.enable}")
    private int enable;

    @Value("${chopperbot.proxy.address}")
    private String address;

    @Value("${chopperbot.proxy.port}")
    private int port;


    public Proxy httpProxy(){
        if(enable==1){
            return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(address, 7890));
        }
        return null;
    }

    public boolean isEnable(){
        return enable==1;
    }
}
