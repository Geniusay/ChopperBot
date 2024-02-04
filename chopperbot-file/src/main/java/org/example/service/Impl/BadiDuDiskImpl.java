package org.example.service.Impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.example.service.NetworkDiskService;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Description
 * @Author welsir
 * @Date 2023/11/9 17:56
 */
@Component
public class BadiDuDiskImpl implements NetworkDiskService {
    @Override
    public String getToken(String code, String clientId, String clientSecret, String redirectUri) {
        String token = "";
        try {
            redirectUri = redirectUri==null?"oob":redirectUri;
            String url = "https://openapi.baidu.com/oauth/2.0/token?\n" +
                    "grant_type=authorization_code&\n" +
                    "code="+code+"&\n" +
                    "client_id="+clientId+"&\n" +
                    "client_secret="+clientSecret+"&\n" +
                    "redirect_uri="+redirectUri;
            Request build = new Request.Builder().url(url).build();
            OkHttpClient client = new OkHttpClient();
            Response response = null;
            response = client.newCall(build).execute();
            assert response.body() != null;
            String res = response.body().string();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(res);
            System.out.println(jsonNode.toString());
            String refreshToken = jsonNode.get("refresh_token").asText();
            String accessToken = jsonNode.get("access_token").asText();
            System.out.println("refresh_token: " + refreshToken);
            System.out.println("access_token: " + accessToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return token;
    }
}
