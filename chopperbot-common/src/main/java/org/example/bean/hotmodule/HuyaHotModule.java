package org.example.bean.hotmodule;

import org.example.bean.HotModule;
import org.example.bean.live.HuyaLive;

/**
 * @author Genius
 * @date 2023/10/17 00:42
 **/
public class HuyaHotModule extends HotModule<HuyaLive> {

    public HuyaHotModule(String tagId, String tagName) {
        super(tagId, tagName);
    }
}
