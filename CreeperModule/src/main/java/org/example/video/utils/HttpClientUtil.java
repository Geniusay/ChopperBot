package org.example.video.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.example.video.pool.HttpClientPool;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * 简单的get,post请求工具类
 * @author 燧枫
 * @date 2023/5/16 19:24
*/
public class HttpClientUtil {

    private static final CloseableHttpClient httpClient = HttpClientPool.getInstance().getHttpClient();

    // get请求
    public static String get(String url) {
        return get(url, null);
    }

    // post请求
    public static String post(String url, String json) {
        return post(url, json, null);
    }

    // get请求，带请求头
    public static String get(String url, Map<String, String> headers) {
        HttpGet httpGet = new HttpGet(url);
        addHeaders(httpGet, headers);
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            return handleResponse(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // post请求，带请求头
    public static String post(String url, String json, Map<String, String> headers) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));
        addHeaders(httpPost, headers);
        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            return handleResponse(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 统一处理请求头
    private static void addHeaders(HttpRequest httpRequest, Map<String, String> headers) {
        if (headers != null && !headers.isEmpty()) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpRequest.addHeader(entry.getKey(), entry.getValue());
            }
        }
    }

    // 统一封装成response
    private static String handleResponse(HttpResponse response) {
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            try {
                return EntityUtils.toString(entity, StandardCharsets.UTF_8);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new RuntimeException("Response entity is null");
        }
    }
}
