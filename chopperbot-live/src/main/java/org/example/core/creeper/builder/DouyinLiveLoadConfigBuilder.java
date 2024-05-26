package org.example.core.creeper.builder;

import org.example.bean.live.DouyinLive;
import org.example.constpool.ConstPool;
import org.example.constpool.FileNameBuilder;
import org.example.core.creeper.loadconfig.DouyinLiveOnlineConfig;
import org.example.core.manager.CommonLoadConfigBuilder;
import org.example.pool.LiveModuleConstPool;
import org.springframework.stereotype.Component;

/**
 * @author dhx
 * @date 2024/5/26 11:03
 */
@Component
public class DouyinLiveLoadConfigBuilder  extends CommonLoadConfigBuilder<DouyinLiveOnlineConfig> {

    @Override
    public DouyinLiveOnlineConfig build(Object obj) {
        if(obj instanceof DouyinLive){
            String liveId = ((DouyinLive) obj).getLiveId();
            String liver = ((DouyinLive) obj).getLiveName();
            String path = LiveModuleConstPool.getPlatformLiveSavePath(ConstPool.PLATFORM.DOUYIN);
            String showTime = ((DouyinLive) obj).getShowTime();
            DouyinLiveOnlineConfig douyinLiveOnlineConfig = new DouyinLiveOnlineConfig(liveId, path, null, false);
            douyinLiveOnlineConfig.setShowTime(showTime);
            douyinLiveOnlineConfig.setVideoName(FileNameBuilder.buildVideoFileNameNoSuffix(liver,douyinLiveOnlineConfig.getStartTime()));
            douyinLiveOnlineConfig.setShowDownloadTable(true);
            douyinLiveOnlineConfig.setLiverName(liver);
            douyinLiveOnlineConfig.setRoomName(((DouyinLive) obj).getLiveName());
            return douyinLiveOnlineConfig;
        }
        return null;
    }
}
