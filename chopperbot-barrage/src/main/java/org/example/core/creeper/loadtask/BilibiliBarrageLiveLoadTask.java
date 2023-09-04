package org.example.core.creeper.loadtask;

import org.example.constpool.CreeperModuleConstPool;
import org.example.core.creeper.loadconfig.BilibiliLiveLoadBarrageConfig;
import org.example.core.creeper.pipline.BarragePipelineWriteJson;
import org.example.core.creeper.processor.BilibiliBarrageLiveProcessor;
import org.example.core.loadtask.ASyncLoadTask;

import org.example.pojo.Barrage;
import org.example.utils.CreeperConfig;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;

import java.util.List;

/**
 * (B站直播)一个任务
 * @author 燧枫
 * @date 2023/4/23 18:19
*/
public class BilibiliBarrageLiveLoadTask extends ASyncLoadTask<List<? extends Barrage>> {

    private final BilibiliBarrageLiveProcessor bilibiliBarrageLiveProcessor;

    private final BarragePipelineWriteJson barragePipelineWriteJson;

    public BilibiliBarrageLiveLoadTask(BilibiliLiveLoadBarrageConfig LoadBarrageConfig) {
        super(LoadBarrageConfig);
        bilibiliBarrageLiveProcessor = new BilibiliBarrageLiveProcessor(loadConfig.getUrl());
        barragePipelineWriteJson = new BarragePipelineWriteJson(LoadBarrageConfig);
    }

    @Override
    public List<? extends Barrage> start() {
        Spider spider = Spider.create(bilibiliBarrageLiveProcessor)
                // 设置起始Request
                .addRequest(new Request(loadConfig.getUrl()))
                // 设置结果处理类
                .addPipeline(barragePipelineWriteJson)
                // 设置抓取线程数（可根据需要调整）
                .thread(5)
                .setEmptySleepTime(100);
        spider.start();
        while (isRunning()){
            flushCacheAndSave("-1");
        }
        spider.stop();
        return barragePipelineWriteJson.getResult();
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
        return barragePipelineWriteJson.getCacheSize();
    }

    @Override
    public int flushCacheAndSave(String key) {
        return barragePipelineWriteJson.writeDataToFileAndFlushCache(key);
    }
}
