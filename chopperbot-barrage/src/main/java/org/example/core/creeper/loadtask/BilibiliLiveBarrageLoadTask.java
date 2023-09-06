package org.example.core.creeper.loadtask;

import org.example.bean.barrage.BilibiliBarrage;
import org.example.constpool.ConstPool;
import org.example.core.BarrageTaskMonitor;
import org.example.core.creeper.loadconfig.BilibiliLiveLoadBarrageConfig;
import org.example.core.creeper.pipline.BarragePipelineWriteJson;
import org.example.core.creeper.processor.BilibiliBarrageLiveProcessor;
import org.example.core.factory.SpiderFactory;
import org.example.core.loadtask.ASyncLoadTask;

import org.example.core.taskmonitor.Monitor;
import org.example.log.ChopperLogFactory;
import org.example.log.LoggerType;
import org.example.util.ThreadUtil;
import us.codecraft.webmagic.Spider;

import java.util.List;

/**
 * (B站直播)一个任务
 * @author 燧枫
 * @date 2023/4/23 18:19
*/
@Monitor(clazz = BarrageTaskMonitor.class)
public class BilibiliLiveBarrageLoadTask extends ASyncLoadTask<List<BilibiliBarrage>> {


    public BilibiliLiveBarrageLoadTask(BilibiliLiveLoadBarrageConfig LoadBarrageConfig) {
        super(LoadBarrageConfig);
    }

    @Override
    public List<BilibiliBarrage> start() {
        ChopperLogFactory.getLogger(LoggerType.Barrage).info("正在爬取{}直播弹幕....",((BilibiliLiveLoadBarrageConfig)loadConfig).getAnchorName());
        BilibiliBarrageLiveProcessor bilibiliBarrageLiveProcessor = new BilibiliBarrageLiveProcessor(loadConfig.getUrl());
        BarragePipelineWriteJson<BilibiliBarrage> barragePipelineWriteJson =
                new BarragePipelineWriteJson<BilibiliBarrage>((BilibiliLiveLoadBarrageConfig)loadConfig);
       Spider spider = SpiderFactory.buildSpider(
               ConstPool.BILIBILI,bilibiliBarrageLiveProcessor,loadConfig.getUrl()
       );
       spider.addPipeline(barragePipelineWriteJson).start();
        while (bilibiliBarrageLiveProcessor.isRunning()&&!ThreadUtil.isInterrupt()){
            barragePipelineWriteJson.writeDataToFileAndFlushCache("-1");
        }
        spider.stop();
        return barragePipelineWriteJson.getResult();
    }

    @Override
    public void end() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public int getCacheSize() {
        return 0;
    }

    @Override
    public int flushCacheAndSave(String key) {
        return 0;
    }
}
