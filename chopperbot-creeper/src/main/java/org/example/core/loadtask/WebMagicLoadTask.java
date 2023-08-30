package org.example.core.loadtask;

import org.example.core.loadconfig.LoadConfig;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Spider;

/**
 * @author Genius
 * @date 2023/08/04 01:07
 **/
public abstract class WebMagicLoadTask<T> extends CommonLoadTask<T>{


    public WebMagicLoadTask(LoadConfig loadConfig){
        super(loadConfig);
    }

    protected T getData(Spider spider){
        return this.getData(spider,loadConfig.getUrl());
    }
    protected T getData(Spider spider, String url){
        T data = ((ResultItems) spider.get(url)).get("data");
        spider.close();
        return data;
    }

    protected T getData(Spider spider, String url,String key){
        T data = ((ResultItems) spider.get(url)).get(key);
        spider.close();
        return data;
    }

    @Override
    public void restore(){

    };
}
