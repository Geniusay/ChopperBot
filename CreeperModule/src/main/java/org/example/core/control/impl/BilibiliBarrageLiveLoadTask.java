package org.example.core.control.impl;

import org.example.constpool.CreeperModuleConstPool;
import org.example.core.control.LoadTask;
import org.example.core.factory.ProcessorFactory;
import org.example.core.pipeline.PipelineWriteJson;
import org.example.core.processor.barrage.BilibiliBarrageLiveProcessor;
import org.example.pojo.download.LoadBarrageConfig;
import org.example.utils.CreeperConfig;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;

/**
 * (B站直播)一个任务
 * @author 燧枫
 * @date 2023/4/23 18:19
*/
public class BilibiliBarrageLiveLoadTask implements LoadTask {

    private final int threadCnt = CreeperConfig.getIntProperty("bi.threadCnt");

    private final int emptySleepTime = CreeperConfig.getIntProperty("bi.emptySleepTime");

    private final BilibiliBarrageLiveProcessor bilibiliBarrageLiveProcessor;

    private final PipelineWriteJson pipelineWriteJson;

    public BilibiliBarrageLiveLoadTask(LoadBarrageConfig loadBarrageConfig) {
        bilibiliBarrageLiveProcessor = (BilibiliBarrageLiveProcessor) new ProcessorFactory().getProcessor(loadBarrageConfig);
        pipelineWriteJson = new PipelineWriteJson(loadBarrageConfig);
    }

    @Override
    public Object start() {
        Spider.create(bilibiliBarrageLiveProcessor)
                // 设置起始Request
                .addRequest(new Request(CreeperModuleConstPool.OCCUURL))
                // 设置结果处理类
                .addPipeline(pipelineWriteJson)
                // 设置抓取线程数（可根据需要调整）
                .thread(threadCnt)
                .setEmptySleepTime(emptySleepTime)
                // 开始抓取
                .start();
        return null;
    }

    @Override
    public void end() {
        bilibiliBarrageLiveProcessor.end();
    }

    @Override
    public boolean isRunning() {
        return bilibiliBarrageLiveProcessor.isRunning();
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
