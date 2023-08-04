package org.example.core.control;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Spider;

/**
 * @author Genius
 * @date 2023/08/04 01:07
 **/
public abstract class CommonLoadTask<T> implements LoadTask<T>{

    protected T getData(Spider spider, String url){
        T data = ((ResultItems) spider.get(url)).get("data");
        spider.close();
        return data;
    }
}
