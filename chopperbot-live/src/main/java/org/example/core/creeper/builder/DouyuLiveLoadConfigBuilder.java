package org.example.core.creeper.builder;

import org.example.bean.live.BiliBiliLive;
import org.example.bean.live.DouyuLive;
import org.example.constpool.ConstPool;
import org.example.core.creeper.loadconfig.BilibiliLiveOnlineConfig;
import org.example.core.creeper.loadconfig.DouyuLiveOnlineConfig;
import org.example.core.creeper.loadconfig.LoadLiveConfig;
import org.example.core.manager.CommonLoadConfigBuilder;
import org.example.pool.LiveModuleConstPool;
import org.springframework.stereotype.Component;

/**
 * @author Genius
 * @date 2023/09/05 19:27
 **/
@Component
public class DouyuLiveLoadConfigBuilder extends CommonLoadConfigBuilder<DouyuLiveOnlineConfig> {

    @Override
    public DouyuLiveOnlineConfig build(Object obj) {
        if(obj instanceof DouyuLive){
            String liveId = ((DouyuLive) obj).getLiveId();
            String liver = ((DouyuLive) obj).getLiver();
            String path = LiveModuleConstPool.getPlatformLiveSavePath(ConstPool.PLATFORM.BILIBILI);
            String showTime = ((DouyuLive) obj).getShowTime();
            DouyuLiveOnlineConfig douyuLiveOnlineConfig = new DouyuLiveOnlineConfig(liveId, path, LoadLiveConfig.fileName(liveId, liver,showTime), true);

            douyuLiveOnlineConfig.setShowDownloadTable(true);
            douyuLiveOnlineConfig.setLiverName(liver);
            douyuLiveOnlineConfig.setRoomName(((DouyuLive) obj).getLiveName());
            douyuLiveOnlineConfig.setStartTime(showTime);
            return douyuLiveOnlineConfig;
        }
        return null;
    }
}
