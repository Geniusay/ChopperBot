package org.example.pool;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

/**
 * http请求链接池
 * @author 燧枫
 * @date 2023/5/16 19:22
*/
public class HttpClientPool {

    private static final int MAX_TOTAL_CONNECTIONS = 100;
    private static final int DEFAULT_MAX_PER_ROUTE = 20;
    private static HttpClientPool instance;
    private final CloseableHttpClient httpClient;

    private HttpClientPool() {
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
        connManager.setMaxTotal(MAX_TOTAL_CONNECTIONS);
        connManager.setDefaultMaxPerRoute(DEFAULT_MAX_PER_ROUTE);
        this.httpClient = HttpClients.custom().setConnectionManager(connManager).build();
    }

    public static synchronized HttpClientPool getInstance() {
        if (instance == null) {
            instance = new HttpClientPool();
        }
        return instance;
    }

    public CloseableHttpClient getHttpClient() {
        return httpClient;
    }
}
