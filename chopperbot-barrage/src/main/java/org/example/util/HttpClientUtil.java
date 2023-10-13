package org.example.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * 简单的get,post请求工具类
 * @author 燧枫
 * @date 2023/5/16 19:24
*/
public class HttpClientUtil {

    private static final CloseableHttpClient httpClient = HttpClients.createDefault();

    public static String get(String url) {
        return executeRequest(new HttpGet(url));
    }

    public static String get(String url, Map<String, String> headers) {
        HttpGet httpGet = new HttpGet(url);
        addHeaders(httpGet, headers);
        return executeRequest(httpGet);
    }

    public static String post(String url){
        return executeRequest(new HttpPost(url));
    }

    public static String post(String url, String json) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));
        return executeRequest(httpPost);
    }

    public static String post(String url, String json, Map<String, String> headers) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));
        addHeaders(httpPost, headers);
        return executeRequest(httpPost);
    }

    public static String post(String url, UrlEncodedFormEntity urlEncodedFormEntity, Map<String, String> headers) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(urlEncodedFormEntity);
        addHeaders(httpPost, headers);
        return executeRequest(httpPost);
    }

    public static String put(String url) {
        return executeRequest(new HttpPut(url));
    }

    public static String put(String url, String json) {
        HttpPut httpPut = new HttpPut(url);
        httpPut.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));
        return executeRequest(httpPut);
    }

    public static String put(String url, String json, Map<String, String> headers) {
        HttpPut httpPut = new HttpPut(url);
        httpPut.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));
        addHeaders(httpPut, headers);
        return executeRequest(httpPut);
    }

    public static String delete(String url) {
        return executeRequest(new HttpDelete(url));
    }

    public static String delete(String url, Map<String, String> headers) {
        HttpDelete httpDelete = new HttpDelete(url);
        addHeaders(httpDelete, headers);
        return executeRequest(httpDelete);
    }

    private static String executeRequest(HttpUriRequest request) {
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            return handleResponse(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void addHeaders(HttpRequest httpRequest, Map<String, String> headers) {
        if (headers != null && !headers.isEmpty()) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpRequest.addHeader(entry.getKey(), entry.getValue());
            }
        }
    }

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
