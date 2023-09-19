package org.example.core.bgevnet.instantslicing;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.example.cache.FileCache;
import org.example.cache.FileCacheManagerInstance;
import org.example.config.BarrageModuleConfig;
import org.example.constpool.PluginName;
import org.example.core.taskcenter.task.ReptileTask;
import org.example.plugin.SpringBootPlugin;
import org.springframework.stereotype.Component;

/**
 * @author Genius
 * @date 2023/09/15 17:11
 **/
@Data
@Component
public class InstantSlicingPlugin extends SpringBootPlugin{

   private InstantSlicingHandler handler;

   private String handlerName;

    @Override
    public boolean init() {
        FileCache fileCache = FileCacheManagerInstance.getInstance().getFileCache(BarrageModuleConfig.getFullFilePath());
        this.handlerName = (String) fileCache.get(PluginName.INSTANT_SLICING_PLUGIN, "handler");
        if(handlerName==null){
            this.error("Invalid handlerName!");
            return false;
        }
        JSONObject config = JSONObject.parseObject(JSON.toJSONString(fileCache.get(PluginName.INSTANT_SLICING_PLUGIN,handlerName)));
        if(handlerName.equals("ScheduleTime")){
            handler = new ScheduleTimeHandler(config);
        }else {
            return false;
        }
        this.info(String.format("Instant Slicing Handler:%s", handlerName));
        handler.handler();
        return super.init();
    }



    public void removeTask(String taskId){
        handler.removeTask(taskId);
    }

    public void addTask(ReptileTask task) {
        this.info(String.format("Add a task:%s for instant slicing",task.getTaskId()));
        handler.addTask(task);
    }


    @Override
    public void shutdown() {
        handler.shutdown();
    }

}
