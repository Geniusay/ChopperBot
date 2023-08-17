package org.example.config;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Genius
 * @date 2023/08/06 21:56
 **/

@Data
@AllArgsConstructor
public class SpiderConfig {
    private String userAgent;
    private int retryTimes;
    private int retrySleepTime;
    private int threadCnt;
    private int emptySleepTime;
    private int sleepTime;

    public SpiderConfig() {
        userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.0.0 Safari/537.36 Edg/112.0.1722.48";
        retryTimes = 3;
        retrySleepTime = 100;
        threadCnt = 5;
        emptySleepTime = 100;
        sleepTime = 100;
    }
}
