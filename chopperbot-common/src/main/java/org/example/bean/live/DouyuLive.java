package org.example.bean.live;

import lombok.Data;
import org.example.bean.Barrage;
import org.example.bean.Live;
import org.example.constpool.ConstPool;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Genius
 * @date 2023/07/19 01:16
 **/
@Data
public class DouyuLive extends Live {



    private String uid;


    public DouyuLive(int watcherNum, String liveId, String liveName, String liver, String description,
                    String roomCoverPic, String uid, String moduleId,String moduleName) {
        super(watcherNum, liveId, liveName, liver, description, ConstPool.PLATFORM.DOUYU.getName(),moduleId,moduleName);
        setRoomPic(roomCoverPic);
        this.uid = uid;
    }
}
