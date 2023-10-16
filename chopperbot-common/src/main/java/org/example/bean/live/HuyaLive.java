package org.example.bean.live;

import lombok.Data;
import org.example.bean.Live;

/**
 * @author Genius
 * @date 2023/10/16 22:04
 **/
@Data
public class HuyaLive extends Live {

    private String uid;

    public HuyaLive(String uid) {
        this.uid = uid;
    }

    public HuyaLive(int watcherNum, String liveId, String liveName, String description) {
        super(watcherNum, liveId, liveName, description);
    }

    public HuyaLive(int watcherNum, String liveId, String liveName, String liver, String description) {
        super(watcherNum, liveId, liveName, liver, description);
    }

    public HuyaLive(int watcherNum, String liveId, String liveName, String liver, String description, String platform) {
        super(watcherNum, liveId, liveName, liver, description, platform);
    }

    public HuyaLive(int watcherNum, String liveId, String liveName, String liver, String description, String platform, String moduleId, String moduleName) {
        super(watcherNum, liveId, liveName, liver, description, platform, moduleId, moduleName);
    }
}
