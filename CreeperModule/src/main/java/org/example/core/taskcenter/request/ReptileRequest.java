package org.example.core.taskcenter.request;

import org.example.core.Callback;
import org.example.core.loadconfig.LoadConfig;

import java.io.*;

/**
 * @author Genius
 * @date 2023/07/28 17:32
 **/
public class ReptileRequest<T extends LoadConfig> implements Serializable {

    protected Callback callback;

    protected T loadConfig;

    public ReptileRequest(T loadConfig,Callback callback) {

        this.loadConfig = loadConfig;
        this.callback = callback;
    }

    public T getLoadConfig() {
        return loadConfig;
    }

    public void setLoadConfig(T loadConfig) {
        this.loadConfig = loadConfig;
    }

    public String GenerateTaskId(){
        return loadConfig.getTaskId();
    }

    public void response(Object obj){
        callback.callback(obj);
    }

}
