package org.example.core.bgevnet;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.constpool.PluginName;
import org.example.core.bgevnet.bgscore.BarrageScoreCurvePlugin;
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
        this.info(String.format("accept a event:%s", event.getLiver()));
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
            List<Barrage> list = PluginCheckAndDo.CheckAndGet(
                    plugin -> {
                        return ((BarrageScoreCurvePlugin) plugin).generateCurve(event);
                    }, PluginName.BARRAGE_SCORE_CURVE_PLUGIN, List.class
            );

            //TODO 弹幕评分切割
            //TODO 弹幕标签插件
        }
    }
}
