package org.example.core.creeper.pipline;

import org.example.bean.Barrage;
import org.example.cache.FileCache;
import org.example.core.creeper.file.BarrageSaveFile;
import org.example.core.creeper.loadconfig.LoadBarrageConfig;
import org.example.exception.FileCacheException;
import org.example.log.LoggerType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * (Json格式)数据保存
 *
 * @author 燧枫
 * @date 2023/4/23 19:17
 */
public class BarragePipelineWriteJson<T extends Barrage> implements Pipeline {

    private FileCache filecache;
    private final ConcurrentLinkedQueue<T> cache;
    LoadBarrageConfig loadBarrageConfig;

    private BarrageSaveFile<T> barrageSaveFile;

    private int successCount = 0;

    List<T> res = new ArrayList<>();

    private AtomicInteger alreadyCount = new AtomicInteger(0);

    private ReentrantLock lock = new ReentrantLock();

    public BarragePipelineWriteJson(LoadBarrageConfig loadBarrageConfig) {
        try {
            this.loadBarrageConfig = loadBarrageConfig;
            this.cache = new ConcurrentLinkedQueue<>();
            this.barrageSaveFile = new BarrageSaveFile(loadBarrageConfig, cache);
            this.filecache = new FileCache(barrageSaveFile, 0, 10 * 1024);
        } catch (FileCacheException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        List<T> barrageList = resultItems.get("barrageList");

        try {
            if (barrageList != null) {
                lock.lock();
                alreadyCount.getAndIncrement();
                Collections.sort(barrageList);
                cache.addAll(barrageList);
            }
        }finally {
            alreadyCount.getAndDecrement();
            lock.unlock();
        }

    }

    public int getCacheSize() {
        return cache.size();
    }

    // 将缓存写入到别处并且清空缓存
    public int writeDataToFileAndFlushCache(String...keys) {
        Logger logger = LoggerFactory.getLogger(LoggerType.Barrage.name());
        T barrage;
        while (alreadyCount.get()!=0||cache.size()!=0) {
            if((barrage = cache.poll()) != null){
                if (successCount!=0&&successCount % 1000 == 0) {
                    filecache.forceSync();
                    logger.info(filecache.getFileName()+"write in {} barrages",successCount);
                }
                try {
                    filecache.append(barrage, keys);
                } catch (InterruptedException | FileCacheException e) {
                    throw new RuntimeException(e);
                }
                res.add(barrage);
                successCount++;
            }
        }
        filecache.forceSync();
        return successCount;
    }

    public List<T> getResult(){
        return res;
    }


}
