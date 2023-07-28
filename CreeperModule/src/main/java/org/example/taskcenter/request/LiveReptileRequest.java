package org.example.taskcenter.request;

import org.example.bean.Live;

import java.util.function.Consumer;

/**
 * @author Genius
 * @date 2023/07/28 17:33
 **/

//直播爬取请求
public class LiveReptileRequest extends ReptileRequest<Live>{

    public enum LiveType{
        Online,//在线直播爬取
        Record //录播爬取
    }

    private LiveType type;

    public LiveReptileRequest(Live requestObj, LiveType type, Consumer callBack) {
        super(requestObj,callBack);
        this.type = type;
    }

    public LiveType getType() {
        return type;
    }

    public void setType(LiveType type) {
        this.type = type;
    }

    public boolean isOnline(){
        return type==LiveType.Online;
    }

    @Override
    public String GenerateTaskId() {
        return requestObj.getPlatform()+"-"+requestObj.getLiver();
    }
}
