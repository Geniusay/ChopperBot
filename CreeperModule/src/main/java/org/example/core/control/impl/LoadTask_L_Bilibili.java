package org.example.core.control.impl;

import org.example.constpool.ConstPool;
import org.example.core.control.LoadTask;
import org.example.core.factory.ProcessorFactory;
import org.example.core.pipeline.PipelineWriteJson;
import org.example.core.processor.Process_L_Bilibili;
import org.example.pojo.download.LoadConfig;
import org.example.utils.PachongConfig;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;

/**
 * (B站直播)一个任务
 * @author 燧枫
 * @date 2023/4/23 18:19
*/
public class LoadTask_L_Bilibili implements LoadTask {

    private final int threadCnt = PachongConfig.getIntProperty("bi.threadCnt");

    private final int emptySleepTime = PachongConfig.getIntProperty("bi.emptySleepTime");

    private final Process_L_Bilibili process_l_bilibili;

    private final PipelineWriteJson pipelineWriteJson;

    public LoadTask_L_Bilibili(LoadConfig loadConfig) {
        process_l_bilibili = (Process_L_Bilibili) new ProcessorFactory().getProcessor(loadConfig);
        pipelineWriteJson = new PipelineWriteJson(loadConfig);
    }

    @Override
    public void start() {
        Spider.create(process_l_bilibili)
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
        process_l_bilibili.end();
    }

    @Override
    public boolean isRunning() {
        return process_l_bilibili.isRunning();
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
