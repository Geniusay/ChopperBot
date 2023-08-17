package org.example.core.taskcenter.request;

import com.alibaba.fastjson.JSONObject;
import org.example.bean.Barrage;
import org.example.bean.live.DouyuLive;
import org.example.core.Callback;
import org.example.pojo.download.LoadConfig;
import org.example.util.JsonFileUtil;
import org.example.util.SerializeUtil;

import java.io.*;
import java.nio.Buffer;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author Genius
 * @date 2023/07/28 17:32
 **/
public class ReptileRequest<T extends LoadConfig> implements Serializable {

    protected Callback callback;
    protected T requestObj;

    protected T loadConfig;

    public ReptileRequest(T loadConfig,Callback callback) {

        this.loadConfig = requestObj;
        this.callback = callback;
    }

    public T getLoadConfig() {
        return loadConfig;
    }

    public void setLoadConfig(T loadConfig) {
        this.loadConfig = loadConfig;
    }

    public String GenerateTaskId(){
        return String.valueOf(requestObj.hashCode());
    }

    public void response(Object obj){
        callback.callback(obj);
    }

}
