package org.example.danmaku.core.control.impl;

import org.example.constpool.ConstPool;
import org.example.danmaku.core.control.LoadTask;
import org.example.danmaku.core.factory.ProcessorFactory;
import org.example.danmaku.core.pipeline.PipelineWriteJson;
import org.example.danmaku.core.processor.BilibiliLiveProcessor;
import org.example.danmaku.pojo.download.LoadConfig;
import org.example.danmaku.utils.CreeperConfig;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;

/**
 * (B站直播)一个任务
 * @author 燧枫
 * @date 2023/4/23 18:19
*/
public class BilibiliLiveLoadTask implements LoadTask {

    private final int threadCnt = CreeperConfig.getIntProperty("bi.threadCnt");

    private final int emptySleepTime = CreeperConfig.getIntProperty("bi.emptySleepTime");

    private final BilibiliLiveProcessor bilibiliLiveProcessor;

    private final PipelineWriteJson pipelineWriteJson;

    public BilibiliLiveLoadTask(LoadConfig loadConfig) {
        bilibiliLiveProcessor = (BilibiliLiveProcessor) new ProcessorFactory().getProcessor(loadConfig);
        pipelineWriteJson = new PipelineWriteJson(loadConfig);
    }

    @Override
    public void start() {
        Spider.create(bilibiliLiveProcessor)
                // 设置起始Request
                .addRequest(new Request(ConstPool.OCCUURL))
                // 设置结果处理类
                .addPipeline(pipelineWriteJson)
                // 设置抓取线程数（可根据需要调整）
                .thread(threadCnt)
                .setEmptySleepTime(emptySleepTime)
                // 开始抓取
                .start();
    }

    @Override
    public void end() {
        bilibiliLiveProcessor.end();
    }

    @Override
    public boolean isRunning() {
        return bilibiliLiveProcessor.isRunning();
    }

    @Override
    public int getCacheSize() {
        return pipelineWriteJson.getCacheSize();
    }

    @Override
    public int flushCacheAndSave(String key) {
        return pipelineWriteJson.writeDataToFileAndFlushCache(key);
    }
}
