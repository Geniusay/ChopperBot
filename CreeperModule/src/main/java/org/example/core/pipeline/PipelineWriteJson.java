package org.example.core.pipeline;

import org.example.cache.FileCache;
import org.example.cache.FileCacheManagerInstance;
import org.example.exception.FileCacheException;
import org.example.pojo.Barrage;
import org.example.pojo.configfile.BarrageSaveFile;
import org.example.pojo.download.LoadConfig;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * (Json格式)数据保存
 * @author 燧枫
 * @date 2023/4/23 19:17
*/
public class PipelineWriteJson implements Pipeline {

        private FileCache filecache;
        private final ConcurrentLinkedQueue<Barrage> cache;
        LoadConfig loadConfig;

        private BarrageSaveFile barrageSaveFile;

        public PipelineWriteJson(LoadConfig loadConfig) throws FileCacheException {
            this.loadConfig = loadConfig;
            this.cache = new ConcurrentLinkedQueue<>();
            this.barrageSaveFile = new BarrageSaveFile(loadConfig,cache);
            this.filecache = new FileCache(barrageSaveFile);
            FileCacheManagerInstance.getInstance().addFileCache(filecache);
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
                try {
                    filecache.append(barrage,"-1");
                } catch (InterruptedException |FileCacheException  e) {
                    throw new RuntimeException(e);
                }
                successCount++;
            }

            return successCount;
        }

}
