package org.example.core.control.impl;

import org.example.constpool.ConstPool;
import org.example.core.control.LoadTask;
import org.example.core.factory.ProcessorFactory;
import org.example.core.pipeline.PipelineWriteJson;
import org.example.core.processor.Process_R_Douyu;
import org.example.pojo.download.LoadConfig;
import org.example.utils.PachongConfig;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;

/**
 * (斗鱼录播)一个任务
 * @author 燧枫
 * @date 2023/4/23 18:12
*/
public class LoadTask_R_Douyu implements LoadTask {

    private final int threadCnt = PachongConfig.getIntProperty("dy.threadCnt");

    private final int emptySleepTime = PachongConfig.getIntProperty("dy.emptySleepTime");

    private final Process_R_Douyu process_r_douyu;

    private final PipelineWriteJson pipelineWriteJson;

    public LoadTask_R_Douyu(LoadConfig loadConfig) {
        process_r_douyu = (Process_R_Douyu) new ProcessorFactory().getProcessor(loadConfig);
        pipelineWriteJson = new PipelineWriteJson(loadConfig);
    }

    @Override
    public void start() {
        Spider.create(process_r_douyu)
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
        process_r_douyu.end();
    }

    @Override
    public boolean isRunning() {
        return process_r_douyu.isRunning();
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
