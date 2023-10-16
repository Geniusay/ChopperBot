package org.example.core.creeper.builder;

import org.example.bean.live.DouyuLive;
import org.example.bean.live.HuyaLive;
import org.example.constpool.ConstPool;
import org.example.constpool.FileNameBuilder;
import org.example.core.creeper.loadconfig.DouyuLiveOnlineConfig;
import org.example.core.creeper.loadconfig.HuyaLiveOnlineConfig;
import org.example.core.manager.CommonLoadConfigBuilder;
import org.example.pool.LiveModuleConstPool;
import org.springframework.stereotype.Component;

/**
 * @author Genius
 * @date 2023/10/16 22:10
 **/
@Component
public class HuyaLiveLoadConfigBuilder extends CommonLoadConfigBuilder<HuyaLiveOnlineConfig> {
    @Override
    public HuyaLiveOnlineConfig build(Object obj) {
        if(obj instanceof HuyaLive){
            String liveId = ((HuyaLive) obj).getLiveId();
            String liver = ((HuyaLive) obj).getLiver();
            String path = LiveModuleConstPool.getPlatformLiveSavePath(ConstPool.PLATFORM.HUYA);
            String showTime = ((HuyaLive) obj).getShowTime();
            HuyaLiveOnlineConfig huyaLiveOnlineConfig = new HuyaLiveOnlineConfig(liveId, path, null, false);
            huyaLiveOnlineConfig.setShowTime(showTime);
            huyaLiveOnlineConfig.setVideoName(FileNameBuilder.buildVideoFileNameNoSuffix(liver,huyaLiveOnlineConfig.getStartTime()));
            huyaLiveOnlineConfig.setShowDownloadTable(true);
            huyaLiveOnlineConfig.setLiverName(liver);
            huyaLiveOnlineConfig.setRoomName(((HuyaLive) obj).getLiveName());
            return huyaLiveOnlineConfig;
        }
        return null;
    }
}
