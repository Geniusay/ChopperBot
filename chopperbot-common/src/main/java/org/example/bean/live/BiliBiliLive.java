package org.example.bean.live;

import lombok.Data;
import org.example.bean.Live;
import org.example.constpool.ConstPool;

/**
 * @author Genius
 * @date 2023/09/03 14:10
 **/
@Data
public class BiliBiliLive extends Live {

    private String area_id;

    private String area_name;

    private String uid;

    private String parent_area_id;

    private String parent_area_name;

    private String session_id;

    private String group_id;

    public BiliBiliLive(int watcherNum, String liveId, String liveName, String liver, String description,
                        String uid,String area_id, String area_name, String parent_area_id, String parent_area_name, String session_id, String group_id) {
        super(watcherNum, liveId, liveName, liver, description, ConstPool.BILIBILI,area_id,area_name);
        this.uid = uid;
        this.parent_area_id = parent_area_id;
        this.parent_area_name = parent_area_name;
        this.session_id = session_id;
        this.group_id = group_id;
    }
}
