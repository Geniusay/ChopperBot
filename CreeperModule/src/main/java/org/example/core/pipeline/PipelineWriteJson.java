package org.example.core.pipeline;

import org.example.cache.FileCache;
import org.example.exception.FileCacheException;
import org.example.pojo.Barrage;
import org.example.pojo.configfile.BarrageSaveFile;
import org.example.pojo.download.LoadBarrageConfig;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * (Json格式)数据保存
 *
 * @author 燧枫
 * @date 2023/4/23 19:17
 */
public class PipelineWriteJson implements Pipeline {

    private FileCache filecache;
    private final ConcurrentLinkedQueue<Barrage> cache;
    LoadBarrageConfig loadBarrageConfig;

    private BarrageSaveFile barrageSaveFile;

    public PipelineWriteJson(LoadBarrageConfig loadBarrageConfig) {
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
        List<Barrage> barrageList = resultItems.get("barrageList");
        if (barrageList != null) {
            cache.addAll(barrageList);
        }
    }

    public int getCacheSize() {
        return cache.size();
    }

    // 将缓存写入到别处并且清空缓存
    public int writeDataToFileAndFlushCache(String key) {
        int successCount = 0;

        Barrage barrage;
        while ((barrage = cache.poll()) != null) {
            if (successCount % 1000 == 0) {
                System.out.print("写入：" + successCount);
            }
            try {
                filecache.append(barrage, "-1");
            } catch (InterruptedException | FileCacheException e) {
                throw new RuntimeException(e);
            }
            successCount++;
        }
        filecache.forceSync();
        return successCount;
    }

}
