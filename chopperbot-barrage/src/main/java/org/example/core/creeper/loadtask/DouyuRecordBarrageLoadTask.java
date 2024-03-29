package org.example.core.creeper.loadtask;

import org.example.bean.barrage.DouyuBarrage;
import org.example.constpool.ConstPool;
import org.example.core.BarrageTaskMonitor;
import org.example.core.creeper.loadconfig.DouyuRecordLoadBarrageConfig;
import org.example.core.creeper.loadconfig.LoadBarrageConfig;
import org.example.core.creeper.pipline.BarragePipelineWriteJson;
import org.example.core.creeper.processor.DouyuBarrageRecordProcessor;
import org.example.core.factory.SpiderFactory;
import org.example.core.loadtask.ASyncLoadTask;
import org.example.core.taskmonitor.Monitor;
import org.example.util.ThreadUtil;
import us.codecraft.webmagic.Spider;

import java.util.List;

/**
 * (斗鱼录播)一个任务
 * @author 燧枫
 * @date 2023/4/23 18:12
*/
@Monitor(clazz = BarrageTaskMonitor.class)
public class DouyuRecordBarrageLoadTask extends ASyncLoadTask<List<DouyuBarrage>> {


    public DouyuRecordBarrageLoadTask(DouyuRecordLoadBarrageConfig loadBarrageConfig) {
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

        while (douyuBarrageRecordProcessor.isRunning()&&!ThreadUtil.isInterrupt()){
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
        new DouyuRecordBarrageLoadTask(new DouyuRecordLoadBarrageConfig("yjj","0Q8mMYYE18mM49Ad")).start();
    }

}
