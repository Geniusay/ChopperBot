package org.example.bean.live;

import org.example.bean.Live;
import org.example.constpool.ConstPool;

/**
 * @author dhx
 * @date 2024/5/26 11:04
 */
public class DouyinLive  extends Live {



    private String uid;


    public DouyinLive(int watcherNum, String liveId, String liveName, String liver, String description,
                     String roomCoverPic, String uid, String moduleId,String moduleName) {
        super(watcherNum, liveId, liveName, liver, description, ConstPool.PLATFORM.DOUYIN.getName(),moduleId,moduleName);
        setRoomPic(roomCoverPic);
        this.uid = uid;
    }
}
