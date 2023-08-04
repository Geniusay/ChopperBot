package org.example.taskcenter.request;

import lombok.Data;
import org.example.bean.Barrage;

import java.util.function.Consumer;

/**
 * @author Genius
 * @date 2023/07/28 23:06
 **/

//TODO 弹幕请求待完善
public class BarrageReptileRequest extends ReptileRequest<Barrage>{

    private String liver;

    private String roomId;
    private LiveReptileRequest.LiveType liveType;

    public BarrageReptileRequest(Barrage requestObj, Consumer callback, String liver, String roomId, LiveReptileRequest.LiveType liveType) {
        super(requestObj, callback);
        this.liver = liver;
        this.roomId = roomId;
        this.liveType = liveType;
    }

    public LiveReptileRequest.LiveType getLiveType() {
        return liveType;
    }

    public void setLiveType(LiveReptileRequest.LiveType liveType) {
        this.liveType = liveType;
    }

    public boolean isOnline(){
        return liveType== LiveReptileRequest.LiveType.Online;
    }

    @Override
    public String GenerateTaskId() {
        return "barrage-"+liver+"-"+roomId;
    }

    public String getLiver() {
        return liver;
    }

    public String getRoomId() {
        return roomId;
    }
}
