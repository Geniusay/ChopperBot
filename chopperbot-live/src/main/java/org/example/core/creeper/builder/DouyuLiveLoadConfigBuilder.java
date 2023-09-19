package org.example.core.creeper.builder;

import org.example.bean.live.BiliBiliLive;
import org.example.bean.live.DouyuLive;
import org.example.constpool.ConstPool;
import org.example.constpool.FileNameBuilder;
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
            String path = LiveModuleConstPool.getPlatformLiveSavePath(ConstPool.PLATFORM.DOUYU);
            String showTime = ((DouyuLive) obj).getShowTime();
            DouyuLiveOnlineConfig douyuLiveOnlineConfig = new DouyuLiveOnlineConfig(liveId, path, null, true);
            douyuLiveOnlineConfig.setShowTime(showTime);
            douyuLiveOnlineConfig.setVideoName(FileNameBuilder.buildVideoFileNameNoSuffix(liver,douyuLiveOnlineConfig.getStartTime()));
            douyuLiveOnlineConfig.setShowDownloadTable(true);
            douyuLiveOnlineConfig.setLiverName(liver);
            douyuLiveOnlineConfig.setRoomName(((DouyuLive) obj).getLiveName());
            return douyuLiveOnlineConfig;
        }
        return null;
    }
}
