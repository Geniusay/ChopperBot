package org.example.core.creeper.loadtask;

import org.example.bean.barrage.DouyuBarrage;
import org.example.constpool.ConstPool;
import org.example.core.creeper.loadconfig.DouyuLiveLoadBarrageConfig;
import org.example.core.creeper.loadconfig.LoadBarrageConfig;
import org.example.core.creeper.pipline.BarragePipelineWriteJson;
import org.example.core.creeper.processor.DouyuBarrageRecordProcessor;
import org.example.core.factory.SpiderFactory;
import org.example.core.loadtask.ASyncLoadTask;
import us.codecraft.webmagic.Spider;

import java.util.List;

/**
 * (斗鱼录播)一个任务
 * @author 燧枫
 * @date 2023/4/23 18:12
*/
public class DouyuBarrageLoadTask extends ASyncLoadTask<List<DouyuBarrage>> {


    public DouyuBarrageLoadTask(DouyuLiveLoadBarrageConfig loadBarrageConfig) {
        super(loadBarrageConfig);
    }

    @Override
    public List<DouyuBarrage> start() {
        String url = loadConfig.getUrl();
        BarragePipelineWriteJson<DouyuBarrage> barragePipelineWriteJson = new BarragePipelineWriteJson<>((LoadBarrageConfig) loadConfig);
        DouyuBarrageRecordProcessor douyuBarrageRecordProcessor = new DouyuBarrageRecordProcessor(url);


        Spider spider = SpiderFactory.buildSpider(
                ConstPool.DOUYU, douyuBarrageRecordProcessor, url
        );
        spider.addPipeline(barragePipelineWriteJson).start();

        while (douyuBarrageRecordProcessor.isRunning()){
            barragePipelineWriteJson.writeDataToFileAndFlushCache("-1");
        }
        spider.stop();
        return barragePipelineWriteJson.getResult();
    }

    @Override
    public void restore() {

    }

    @Override
    public void end() {

    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public int getCacheSize() {
        return 0;
    }

    @Override
    public int flushCacheAndSave(String key) {
        return  0;
    }

    public static void main(String[] args) {
        new DouyuBarrageLoadTask(new DouyuLiveLoadBarrageConfig("yjj","0Q8mMYYE18mM49Ad")).start();
    }

}
