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
public abstract class AbstractProcessor implements PageProcessor {

    // 运行状态
    AtomicBoolean isRunning = new AtomicBoolean(true);

    // 首次进入
    boolean isFirst = true;

    // 爬虫配置
    protected String userAgent;
    protected int retryTimes;
    protected int retrySleepTime;

    protected final Site site;

    public AbstractProcessor(int retryTimes, int retrySleepTime, String userAgent) {
        this.retryTimes = retryTimes;
        this.retrySleepTime = retrySleepTime;
        this.userAgent = userAgent;
        this.site = Site.me().setUserAgent(userAgent);
    }

    @Override
    public abstract void process(Page page);

    @Override
    public Site getSite() {
        return site.setRetryTimes(retryTimes).setRetrySleepTime(retrySleepTime);
    }

    // 结束掉爬虫
    public void end() {
        this.isRunning.set(false);
    }

    // 爬虫是否在运行
    public boolean isRunning() {
        return this.isRunning.get();
    }
}
