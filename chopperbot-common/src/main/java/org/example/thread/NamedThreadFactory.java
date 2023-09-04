package org.example.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Genius
 * @date 2023/07/21 16:58
 **/

/**
 * 更改线程池名字
 */
public class NamedThreadFactory implements ThreadFactory {
    private final String poolName;
    private final AtomicInteger threadNumber = new AtomicInteger(1);

    public NamedThreadFactory(String poolName) {
        this.poolName = poolName;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setName("ChopperBot-"+poolName + "-" + threadNumber.getAndIncrement());
        return t;
    }
}
