package org.example.utils;

import java.util.Random;

/**
 *
 * @author 燧枫
 * @date 2023/8/3 21:33
 */
public class DriverUtil {

    static Random random = new Random();

    // 随机暂停一段时间
    public static void randomSleep(int minTimeInSeconds, int maxTimeInSeconds) {
        try {
            int sleepTimeInMillis = (minTimeInSeconds + random.nextInt(maxTimeInSeconds - minTimeInSeconds + 1)) * 1000;
            Thread.sleep(sleepTimeInMillis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
