package org.example.core.bgevnet;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.bean.Live;
import org.example.constpool.FileNameBuilder;
import org.example.constpool.PluginName;
import org.example.core.bgevnet.bghot.BarragePopularRangePlugin;
import org.example.core.bgevnet.bghot.PopularRange;
import org.example.core.bgevnet.bgscore.BarragePoint;
import org.example.core.bgevnet.bgscore.BarrageScoreCurvePlugin;
import org.example.core.section.SectionRequest;
import org.example.core.section.VideoSectionWorkShop;
import org.example.plugin.PluginCheckAndDo;
import org.example.plugin.SpringBootPlugin;
import org.example.bean.Barrage;
import org.example.thread.NamedThreadFactory;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Genius
 * @date 2023/09/13 18:18
 **/
@Component
public class BarrageEventCenter extends SpringBootPlugin {

    private ExecutorService workerPool;

    private Map<String, Future> workerMap;

    private ReentrantLock eventLock;

    @Override
    public boolean init() {
        workerPool = Executors.newCachedThreadPool(new NamedThreadFactory(this.pluginName));
        workerMap = new HashMap<>();
        eventLock = new ReentrantLock();
        return super.init();
    }


    public boolean event(BarrageEvent event){
        this.info(String.format("accept %s barrage event", event.getLiver()));
        List<Barrage> barrages = event.getBarrages();
        if(barrages==null)return false;
        String filePath = event.getBarrageFilePath();
        try {
            eventLock.lock();
            if (workerMap.containsKey(filePath)) return false;
            workerMap.put(filePath,workerPool.submit(new BarrageListWorker(event)));
        }finally {
            eventLock.unlock();
        }
        return true;
    }


    @Data
    @AllArgsConstructor
    class BarrageListWorker implements Runnable{

        private BarrageEvent event;

        @Override
        public void run() {
            //计算弹幕曲线
            List<BarragePoint> points = PluginCheckAndDo.CheckAndGet(
                    plugin -> {
                        return ((BarrageScoreCurvePlugin) plugin).generateCurve(event);
                    }, PluginName.BARRAGE_SCORE_CURVE_PLUGIN, List.class
            );

            // 弹幕热门区间
            List<PopularRange> popularRanges = PluginCheckAndDo.CheckAndGet(
                    plugin -> {
                        return ((BarragePopularRangePlugin) plugin).findRange(points);
                    }, PluginName.BARRAGE_POPULAR_RANGE_PLUGIN, List.class
            );

            if(popularRanges!=null){
                PluginCheckAndDo.CheckAndDo(plugin -> {
                    for (PopularRange popularRange : popularRanges) {
                        String liver = event.getLiver();
                        String platform = event.getPlatform();
                        String suffix = event.getSuffix();
                        String action = event.getAction();
                        String fileName = event.getFileName().split("\\.")[0]+suffix;
                        String date = event.getDate();
                        SectionRequest request = new SectionRequest(fileName, action, popularRange.getStartTime(), popularRange.getEndTime(), liver, platform,date);
                        ((VideoSectionWorkShop)plugin).request(request);
                    }
                },PluginName.VIDEO_SECTION_WORK_SHOP);
            }
            //TODO 弹幕标签插件
        }

    }
}
