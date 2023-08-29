package org.example.core.creeper.loadconfig;

import org.example.bean.Live;
import org.example.core.loadconfig.LoadConfig;

/**
 * 直播爬取的配置类最底层
 * @author Genius
 * @date 2023/07/28 23:16
 **/
public class LoadLiveConfig<T extends Live> extends LoadConfig {
    private T live;

    public LoadLiveConfig(T live) {
        this.live = live;
    }

    public T getLive() {
        return live;
    }

    public void setLive(T live) {
        this.live = live;
    }
}
