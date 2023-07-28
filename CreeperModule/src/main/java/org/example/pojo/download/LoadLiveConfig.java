package org.example.pojo.download;

import org.example.bean.Live;
import org.example.pojo.download.LoadConfig;

/**
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
