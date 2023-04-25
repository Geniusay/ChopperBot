package org.example.core.pipeline;

import org.example.pojo.Barrage;
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

    // Barrage缓存
    private final ConcurrentLinkedQueue<Barrage> cache;

    LoadConfig loadConfig;

    public PipelineWriteJson(LoadConfig loadConfig) {
        this.loadConfig = loadConfig;
        this.cache = new ConcurrentLinkedQueue<>();
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

        String filePath = "F:\\" + key + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            Barrage barrage;
            while ((barrage = cache.poll()) != null) {
                writer.write(barrage.toString());
                writer.newLine();
                successCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return successCount;
    }

}
