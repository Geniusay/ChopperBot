package org.example.util;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpMethod;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * 简单的get,post请求工具类
 * @author 燧枫
 * @date 2023/5/16 19:24
*/
public class HttpClientUtil {
    private static final CloseableHttpClient httpClient = HttpClients.createDefault();
    private static final ResponseHandler responseHandler = new DefaultResponseHandler();
    private static final HttpClientExecutor executor = new HttpClientExecutor(httpClient, responseHandler);

    public static String get(String url) {
        HttpUriRequest request = HttpRequestFactory.createRequest(HttpMethod.GET, url, null, null);
        return executor.execute(request);
    }

    public static String get(String url, Map<String, String> headers) {
        HttpUriRequest request = HttpRequestFactory.createRequest(HttpMethod.GET, url, null, headers);
        return executor.execute(request);
    }

    public static String post(String url) {
        HttpUriRequest request = HttpRequestFactory.createRequest(HttpMethod.POST, url, null, null);
        return executor.execute(request);
    }

    public static String post(String url, String json) {
        HttpUriRequest request = HttpRequestFactory.createRequest(HttpMethod.POST, url, json, null);
        return executor.execute(request);
    }

    public static String post(String url, String json, Map<String, String> headers) {
        HttpUriRequest request = HttpRequestFactory.createRequest(HttpMethod.POST, url, json, headers);
        return executor.execute(request);
    }

    public static String put(String url) {
        HttpUriRequest request = HttpRequestFactory.createRequest(HttpMethod.PUT, url, null, null);
        return executor.execute(request);
    }

    public static String put(String url, String json) {
        HttpUriRequest request = HttpRequestFactory.createRequest(HttpMethod.PUT, url, json, null);
        return executor.execute(request);
    }

    public static String delete(String url) {
        HttpUriRequest request = HttpRequestFactory.createRequest(HttpMethod.DELETE, url, null, null);
        return executor.execute(request);
    }

    public static String delete(String url, Map<String, String> headers) {
        HttpUriRequest request = HttpRequestFactory.createRequest(HttpMethod.DELETE, url, null, headers);
        return executor.execute(request);
    }

    public static Map<String, String> getResponseHeaders(String url, HttpMethod method, String json, Map<String, String> headers) {
        HttpUriRequest request = HttpRequestFactory.createRequest(method, url, json, headers);
        return executor.executeForHeaders(request);
    }
}
class HttpClientExecutor {
    private final CloseableHttpClient httpClient;
    private final ResponseHandler responseHandler;

    public HttpClientExecutor(CloseableHttpClient httpClient, ResponseHandler responseHandler) {
        this.httpClient = httpClient;
        this.responseHandler = responseHandler;
    }

    public String execute(HttpUriRequest request) {
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            return responseHandler.handleResponse(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Map<String, String> executeForHeaders(HttpUriRequest request) {
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            return responseHandler.handleResponseHeaders(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
interface ResponseHandler {
    String handleResponse(HttpResponse response) throws IOException;
    Map<String, String> handleResponseHeaders(HttpResponse response) throws IOException;
}

class DefaultResponseHandler implements ResponseHandler {
    @Override
    public String handleResponse(HttpResponse response) throws IOException {
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            return EntityUtils.toString(entity, StandardCharsets.UTF_8);
        } else {
            throw new RuntimeException("Response entity is null");
        }
    }
    public Map<String, String> handleResponseHeaders(HttpResponse response) {
        Header[] headers = response.getAllHeaders();
        Map<String, String> headersMap = new HashMap<>();
        for (Header header : headers) {
            headersMap.put(header.getName(), header.getValue());
        }
        return headersMap;
    }
}

class HttpRequestFactory {
    public static HttpUriRequest createRequest(HttpMethod method, String url, String json, Map<String, String> headers) {
        switch (method) {
            case GET:
                return createGetRequest(url, headers);
            case POST:
                return createPostRequest(url, json, headers);
            case PUT:
                return createPutRequest(url, json, headers);
            case DELETE:
                return createDeleteRequest(url, headers);
            default:
                throw new IllegalArgumentException("Unsupported HTTP method: " + method);
        }
    }

    private static HttpGet createGetRequest(String url, Map<String, String> headers) {
        HttpGet request = new HttpGet(url);
        addHeaders(request, headers);
        return request;
    }

    private static HttpPost createPostRequest(String url, String json, Map<String, String> headers) {
        HttpPost request = new HttpPost(url);
        if(json!=null)request.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));
        addHeaders(request, headers);
        return request;
    }

    private static HttpPut createPutRequest(String url, String json, Map<String, String> headers) {
        HttpPut request = new HttpPut(url);
        if(json!=null)request.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));
        addHeaders(request, headers);
        return request;
    }

    private static HttpDelete createDeleteRequest(String url, Map<String, String> headers) {
        HttpDelete request = new HttpDelete(url);
        addHeaders(request, headers);
        return request;
    }

    private static void addHeaders(HttpRequest request, Map<String, String> headers) {
        if (headers != null && !headers.isEmpty()) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                request.addHeader(entry.getKey(), entry.getValue());
            }
        }
    }
}
