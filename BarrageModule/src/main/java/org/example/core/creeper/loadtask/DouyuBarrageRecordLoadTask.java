package org.example.core.creeper.loadtask;

import org.example.bean.barrage.DouyuBarrage;
import org.example.core.creeper.loadconfig.DouyuRecordLoadBarrageConfig;
import org.example.core.creeper.pipline.BarragePipelineWriteJson;
import org.example.core.creeper.processor.DouyuBarrageRecordProcessor;
import org.example.core.loadtask.ASyncLoadTask;

import us.codecraft.webmagic.Spider;

import java.util.List;

/**
 * (斗鱼录播)一个任务
 * @author 燧枫
 * @date 2023/4/23 18:12
*/
public class DouyuBarrageRecordLoadTask extends ASyncLoadTask<List<DouyuBarrage>> {


    private final DouyuBarrageRecordProcessor douyuBarrageRecordProcessor;

    private final BarragePipelineWriteJson<DouyuBarrage> barragePipelineWriteJson;

    private String url = loadConfig.getUrl();

    public DouyuBarrageRecordLoadTask(DouyuRecordLoadBarrageConfig loadBarrageConfig) {
        super(loadBarrageConfig);
        douyuBarrageRecordProcessor = new DouyuBarrageRecordProcessor(url);

        douyuBarrageRecordProcessor.getSite().setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.0.0 Safari/537.36 Edg/112.0.1722.48")
                .setSleepTime(100)
                .setRetrySleepTime(100)
                .setRetryTimes(3);

        barragePipelineWriteJson = new BarragePipelineWriteJson<DouyuBarrage>(loadBarrageConfig);
        this.url = loadBarrageConfig.getUrl();
    }

    @Override
    public List<DouyuBarrage> start() {
        Spider spider = Spider.create(douyuBarrageRecordProcessor)
                .addPipeline(barragePipelineWriteJson)
                .thread(5)
                .addUrl(url);
        spider.start();
        while (isRunning()){
            flushCacheAndSave("-1");
        }
        spider.stop();
        return barragePipelineWriteJson.getResult();
    }

    @Override
    public void restore() {

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
        return barragePipelineWriteJson.getCacheSize();
    }

    @Override
    public int flushCacheAndSave(String key) {
        return barragePipelineWriteJson.writeDataToFileAndFlushCache(key);
    }

    public static void main(String[] args) {
        new DouyuBarrageRecordLoadTask(new DouyuRecordLoadBarrageConfig("yjj","0Q8mMYYE18mM49Ad")).start();
    }

}
