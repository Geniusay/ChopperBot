package org.example.core.control.impl;

import org.example.bean.Barrage;
import org.example.bean.barrage.DouyuBarrage;
import org.example.constpool.CreeperModuleConstPool;
import org.example.core.control.CommonLoadTask;
import org.example.core.control.LoadTask;
import org.example.core.factory.ProcessorFactory;
import org.example.core.pipeline.PipelineWriteJson;
import org.example.core.processor.barrage.DouyuBarrageRecordProcessor;
import org.example.pojo.download.LoadBarrageConfig;
import org.example.utils.CreeperConfig;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;

import java.util.List;

/**
 * (斗鱼录播)一个任务
 * @author 燧枫
 * @date 2023/4/23 18:12
*/
public class DouyuBarrageRecordLoadTask extends CommonLoadTask<List<DouyuBarrage>> {

    private final int threadCnt = CreeperConfig.getIntProperty("dy.threadCnt");

    private final int emptySleepTime = CreeperConfig.getIntProperty("dy.emptySleepTime");

    private final DouyuBarrageRecordProcessor douyuBarrageRecordProcessor;

    private final PipelineWriteJson pipelineWriteJson;

    public DouyuBarrageRecordLoadTask(LoadBarrageConfig loadBarrageConfig) {
        douyuBarrageRecordProcessor = (DouyuBarrageRecordProcessor) new ProcessorFactory().getProcessor(loadBarrageConfig);
        pipelineWriteJson = new PipelineWriteJson(loadBarrageConfig);
    }

    @Override
    public List<DouyuBarrage> start() {
        List<DouyuBarrage> data = getData(Spider
                .create(douyuBarrageRecordProcessor)
                .thread(threadCnt)
                .addPipeline(pipelineWriteJson),
                CreeperModuleConstPool.OCCUURL);

        return data;
    }

    @Override
    public void end() {
        douyuBarrageRecordProcessor.end();
    }

    @Override
    public boolean isRunning() {
        return douyuBarrageRecordProcessor.isRunning();
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
