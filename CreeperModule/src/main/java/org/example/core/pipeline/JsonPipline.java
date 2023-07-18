package org.example.core.pipeline;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.example.bean.ConfigFile;
import org.example.cache.FileCache;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.ArrayList;

/**
 * @author Genius
 * @date 2023/07/15 21:18
 **/
public class JsonPipline<T> implements Pipeline {

    private String handlerName; // 处理的对象名

    public JsonPipline(String handlerName){
        this.handlerName = handlerName;
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        Object o = resultItems.get(handlerName);
        System.out.println(o);
    }
}
