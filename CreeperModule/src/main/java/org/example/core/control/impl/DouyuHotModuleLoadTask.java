package org.example.core.control.impl;

import org.example.bean.hotmodule.HotModuleList;
import org.example.core.control.HotModuleLoadTask;
import org.example.core.control.LoadTask;
import org.example.core.processor.hotmodule.DouyuHotModuleProcessor;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Spider;

import static org.example.constpool.ApiPool.DOUYU_HOT_MODULE_API;

/**
 * @author Genius
 * @date 2023/07/15 21:04
 **/
public class DouyuHotModuleLoadTask extends HotModuleLoadTask<HotModuleList> {


    @Override
    protected HotModuleList start0() {
        HotModuleList data;
        try {
            data = getData(Spider.create(new DouyuHotModuleProcessor()),DOUYU_HOT_MODULE_API);
        }catch (Exception e){
            fail(e);
            return null;
        }
        success();
        return data;
    }
}
