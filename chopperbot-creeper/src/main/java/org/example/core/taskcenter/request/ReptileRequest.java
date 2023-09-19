package org.example.core.taskcenter.request;

import lombok.Data;
import org.example.core.Callback;
import org.example.core.loadconfig.LoadConfig;

import java.io.*;

/**
 * @author Genius
 * @date 2023/07/28 17:32
 **/
@Data
public class ReptileRequest<T> implements Serializable {

    protected Callback callback;

    protected T param;
    protected String creeperGroup;

    protected String creeperName;

    public ReptileRequest(Callback callback, String creeperGroup) {
        this.callback = callback;
        this.creeperGroup = creeperGroup;
    }

    public ReptileRequest(Callback callback, String creeperGroup,T param) {
        this.callback = callback;
        this.param = param;
        this.creeperGroup = creeperGroup;
    }

    public ReptileRequest(String creeperGroup,T param) {
        this.callback = (t)->{};
        this.param = param;
        this.creeperGroup = creeperGroup;
    }

    public ReptileRequest(Callback callback, String creeperGroup, String creeperName) {
        this.callback = callback;
        this.creeperGroup = creeperGroup;
        this.creeperName = creeperName;
    }


    public void response(Object obj){
        callback.callback(obj);
    }

}
