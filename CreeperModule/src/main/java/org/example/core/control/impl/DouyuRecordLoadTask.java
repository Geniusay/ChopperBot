package org.example.core.control.impl;

import org.example.constpool.CreeperModuleConstPool;
import org.example.core.control.LoadTask;
import org.example.core.factory.ProcessorFactory;
import org.example.core.pipeline.PipelineWriteJson;
import org.example.core.processor.DouyuRecordProcessor;
import org.example.pojo.download.LoadBarrageConfig;
import org.example.utils.CreeperConfig;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;

/**
 * (斗鱼录播)一个任务
 * @author 燧枫
 * @date 2023/4/23 18:12
*/
public class DouyuRecordLoadTask implements LoadTask {

    private final int threadCnt = CreeperConfig.getIntProperty("dy.threadCnt");

    private final int emptySleepTime = CreeperConfig.getIntProperty("dy.emptySleepTime");

    private final DouyuRecordProcessor douyuRecordProcessor;

    private final PipelineWriteJson pipelineWriteJson;

    public DouyuRecordLoadTask(LoadBarrageConfig loadBarrageConfig) {
        douyuRecordProcessor = (DouyuRecordProcessor) new ProcessorFactory().getProcessor(loadBarrageConfig);
        pipelineWriteJson = new PipelineWriteJson(loadBarrageConfig);
    }

    @Override
    public void start() {
        Spider.create(douyuRecordProcessor)
                // 设置起始Request
                .addRequest(new Request(CreeperModuleConstPool.OCCUURL))
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
        douyuRecordProcessor.end();
    }

    @Override
    public boolean isRunning() {
        return douyuRecordProcessor.isRunning();
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
