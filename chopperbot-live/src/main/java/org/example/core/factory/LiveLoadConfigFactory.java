package org.example.core.factory;

import org.example.constpool.ConstPool;
import org.example.core.creeper.loadconfig.BilibiliLiveOnlineConfig;
import org.example.core.creeper.loadconfig.DouyuLiveOnlineConfig;
import org.example.core.creeper.loadconfig.LoadLiveConfig;
import org.example.pool.LiveModuleConstPool;


/**
 * @author Genius
 * @date 2023/08/31 23:57
 **/
public class LiveLoadConfigFactory {

    public static  <T extends LoadLiveConfig> T buildLiveConfig(String platform, String roomId, String liver,
                                                                boolean convertMp4, boolean showDownloadTable){
        platform = platform.toLowerCase();

        if(platform.equals(ConstPool.PLATFORM.DOUYU.getName())){
            String path = LiveModuleConstPool.getPlatformLiveSavePath(ConstPool.PLATFORM.DOUYU);
            DouyuLiveOnlineConfig config = new DouyuLiveOnlineConfig(roomId, path, LoadLiveConfig.fileName(roomId, liver), convertMp4);
            config.setShowDownloadTable(showDownloadTable);
            config.setLiverName(liver);
            return (T) config;
        }else if(platform.equals(ConstPool.PLATFORM.BILIBILI.getName())){
            String path = LiveModuleConstPool.getPlatformLiveSavePath(ConstPool.PLATFORM.BILIBILI);
            BilibiliLiveOnlineConfig config = new BilibiliLiveOnlineConfig(roomId, path, LoadLiveConfig.fileName(roomId, liver), convertMp4);
            config.setShowDownloadTable(showDownloadTable);
            config.setLiverName(liver);
            return (T) config;
        }
        return null;
    }

    public static  <T extends LoadLiveConfig> T buildLiveConfig(String platform, String roomId,String liver,
                                                                int clarity,boolean showDownloadTable){
        platform = platform.toLowerCase();
        if(platform.equals(ConstPool.PLATFORM.DOUYU.getName())){
            String path = LiveModuleConstPool.getPlatformLiveSavePath(ConstPool.PLATFORM.DOUYU);
            DouyuLiveOnlineConfig config = new DouyuLiveOnlineConfig(roomId, path, LoadLiveConfig.fileName(roomId, liver), clarity);
            config.setShowDownloadTable(showDownloadTable);
            config.setLiverName(liver);
            return (T) config;
        }else if(platform.equals(ConstPool.PLATFORM.BILIBILI.getName())){
            String path = LiveModuleConstPool.getPlatformLiveSavePath(ConstPool.PLATFORM.BILIBILI);
            BilibiliLiveOnlineConfig config = new BilibiliLiveOnlineConfig(roomId, path, LoadLiveConfig.fileName(roomId, liver), clarity);
            config.setShowDownloadTable(showDownloadTable);
            config.setLiverName(liver);
            return (T) config;
        }
        return null;
    }


}
