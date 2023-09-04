package org.example.core.factory;

import org.example.core.creeper.loadconfig.BilibiliLiveOnlineConfig;
import org.example.core.creeper.loadconfig.DouyuLiveOnlineConfig;
import org.example.core.creeper.loadconfig.LoadLiveConfig;
import org.example.core.parser.PlatformVideoUrlParser;
import org.example.core.parser.impl.BilibiliFlvUrlParser;
import org.example.core.component.LiveStreamTask;
import org.example.core.parser.impl.DouyuFlvUrlParser;

import java.util.HashMap;
import java.util.Map;

/**
 * 直播下载任务制造工产
 * @author 燧枫
 * @date 2023/5/19 20:13
*/
public class LiveTaskFactory {

    private final Map<Class<? extends LoadLiveConfig>, PlatformVideoUrlParser> parserMap = Map.of(
            BilibiliLiveOnlineConfig.class,new BilibiliFlvUrlParser(),
            DouyuLiveOnlineConfig.class, new DouyuFlvUrlParser()
    );

    public LiveTaskFactory() {
    }

    // 根据LiveConfig去创建下载任务
    public LiveStreamTask create(LoadLiveConfig liveConfig) {
        PlatformVideoUrlParser parser = this.parserMap.get(liveConfig.getClass());
        if (parser == null) {
            throw new IllegalArgumentException("Unsupported live config type: " + liveConfig.getClass());
        }
        try {
            String flvUrl = parser.getUrl(liveConfig);
            LiveStreamTask task = new LiveStreamTask();
            task.setUrl(flvUrl);
            Map<String, String> headers = new HashMap<>();

            headers.put("User-Agent",liveConfig.getUserAgent());
            headers.put("Origin",liveConfig.getOrigin());
            headers.put("Referer",liveConfig.getReferer());
            // 为任务添加请求头
            task.setHeaders(headers);
            // 如果有其他平台的直播，可以在这里添加相应的请求头
            return task;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
