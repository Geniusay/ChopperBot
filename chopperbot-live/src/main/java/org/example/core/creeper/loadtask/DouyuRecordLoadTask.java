package org.example.core.creeper.loadtask;

import org.example.core.component.M3U8Handle;
import org.example.core.creeper.loadconfig.DouyuRecordConfig;
import org.example.core.loadconfig.LoadConfig;
import org.example.core.loadtask.CommonLoadTask;
import org.example.core.parser.impl.DouyuFlvUrlParser;
import org.example.core.parser.impl.DouyuM3u8UrlParser;
import org.example.log.ChopperLogFactory;
import org.example.log.LoggerType;
import org.slf4j.Logger;

/**
 * @author Genius
 * @date 2023/08/30 16:41
 **/


public class DouyuRecordLoadTask extends CommonLoadTask<String> {

    public DouyuRecordLoadTask(DouyuRecordConfig loadConfig) {
        super(loadConfig);
    }



    @Override
    public String start() {
        DouyuRecordConfig douyuRecordConfig = (DouyuRecordConfig)loadConfig;
        Logger logger = ChopperLogFactory.getLogger(LoggerType.LiveRecord);
        String url = null;
        try {
            url = new DouyuM3u8UrlParser().getUrl(douyuRecordConfig);
            M3U8Handle.downloadAndCutVideo(url,
                    douyuRecordConfig.getVideoStartTime(),
                    douyuRecordConfig.getVideoEndTime(),
                    douyuRecordConfig.getVideoPath(),
                    douyuRecordConfig.getVideoName());
        }catch (Exception e){
            logger.info("{} 录播爬取失败,error:{}",douyuRecordConfig.getVideoName(),e.getMessage());
        }
        return url;
    }

    @Override
    public void end() {

    }

}
