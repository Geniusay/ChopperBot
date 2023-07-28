package org.example.taskcenter.request;

import java.util.function.Consumer;

/**
 * @author Genius
 * @date 2023/07/28 17:32
 **/
public abstract class ReptileRequest<T> {

    protected Consumer callback;
    protected T requestObj;

    public ReptileRequest(T requestObj,Consumer callback) {

        this.requestObj = requestObj;
        this.callback = callback;
    }

    public T getRequestObj() {
        return requestObj;
    }

    public void setRequestObj(T requestObj) {
        this.requestObj = requestObj;
    }

    public String GenerateTaskId(){
        return String.valueOf(requestObj.hashCode());
    }

    public void response(Object obj){
        callback.accept(obj);
    }
}
