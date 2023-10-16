package org.example.core.creeper.loadtask;

import org.example.bean.hotmodule.HotModuleList;
import org.example.bean.hotmodule.HuyaHotModule;
import org.example.core.creeper.loadconfig.HuyaHotModuleConfig;
import org.example.core.loadconfig.LoadConfig;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Genius
 * @date 2023/10/17 00:38
 **/
public class HuyaHotModuleLoadTask extends HotModuleLoadTask<HotModuleList>{

    public HuyaHotModuleLoadTask(HuyaHotModuleConfig loadConfig) {
        super(loadConfig);
    }

    @Override
    public HotModuleList start() {
        HotModuleList data = null;
        clearFinishFlag();
        try {
            Document doc = Jsoup.connect(loadConfig.getUrl()).get();
            Element ul = doc.selectFirst("ul#js-game-list");
            if (ul != null) {
                Elements liElements = ul.select("li"); // 获取ul下的所有li元素
                data = new HotModuleList();
                for (Element li : liElements) {
                    String dataGid = li.attr("data-gid");
                    String gName = li.text();
                    data.getHotModuleList().add(new HuyaHotModule(dataGid, gName));
                }
            }
        }catch (Exception e){
            fail(e);
        }
        success();
        return data;
    }
}
