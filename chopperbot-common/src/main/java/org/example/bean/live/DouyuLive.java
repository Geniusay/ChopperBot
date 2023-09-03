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

    private String liveModule;  //直播板块

    private String url;         //直播间地址

    private String roomCoverPic;     //直播间封面地址


    private int type;

    private int uid;


    private List<Barrage> barrages = new ArrayList<>();

    public DouyuLive(int watcherNum, String liveId, String liveName, String liver, String description,
                     String liveModule, String url, String roomCoverPic, int type, int uid, String moduleId,String moduleName) {
        super(watcherNum, liveId, liveName, liver, description, ConstPool.PLATFORM.DOUYU.getName(),moduleId,moduleName);
        this.liveModule = liveModule;
        this.url = url;
        this.roomCoverPic = roomCoverPic;
        this.type = type;
        this.uid = uid;
    }
}
