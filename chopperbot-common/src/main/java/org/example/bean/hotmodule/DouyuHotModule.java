package org.example.bean.hotmodule;

import org.example.bean.HotModule;
import org.example.bean.live.DouyuLive;

/**
 * @author Genius
 * @date 2023/07/18 20:46
 **/
public class DouyuHotModule extends HotModule<DouyuLive> {
    private String url;

    public DouyuHotModule(String tagId, String tagName, String url) {
        super(tagId, tagName);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
