package org.example.core.loadtask;

import org.example.core.loadconfig.LoadConfig;

/**
 * @author Genius
 * @date 2023/08/18 02:31
 **/
public abstract class ASyncLoadTask<T> extends CommonLoadTask<T>{


    protected T result;

    public ASyncLoadTask(LoadConfig loadConfig){
        super(loadConfig);
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
