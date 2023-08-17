package org.example.core.processor;


import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * (抽象父类)下载与处理
 * @author 燧枫
 * @date 2023/4/23 22:17
*/
public abstract class AbstractProcessor implements Processor {

    // 运行状态
    protected AtomicBoolean isRunning = new AtomicBoolean(true);

    // 首次进入
    protected boolean isFirst = true;


    protected final Site site;


    public AbstractProcessor(){
        this.site = Site.me();
    }
    public AbstractProcessor(int retryTimes, int retrySleepTime, String userAgent, int sleepTime) {
        this.site = Site.me()
                .setUserAgent(userAgent)
                .setSleepTime(sleepTime)
                .setRetrySleepTime(retrySleepTime)
                .setRetryTimes(retryTimes);
    }

    @Override
    public abstract void process(Page page);

    @Override
    public Site getSite() {
        return site;
    }

    // 结束掉爬虫
    @Override
    public void end() {
        this.isRunning.set(false);
    }

    // 爬虫是否在运行
    @Override
    public boolean isRunning() {
        return this.isRunning.get();
    }

    //爬虫恢复

}
