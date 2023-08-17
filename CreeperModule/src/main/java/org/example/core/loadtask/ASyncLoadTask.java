package org.example.core.loadtask;

import org.example.pojo.download.LoadConfig;

/**
 * @author Genius
 * @date 2023/08/18 02:31
 **/
public abstract class ASyncLoadTask<T> implements LoadTask<T>{

    protected LoadConfig loadConfig;

    protected T result;

    public ASyncLoadTask(LoadConfig loadConfig){
        this.loadConfig = loadConfig;
    }
    @Override
    public void restore() {

    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
