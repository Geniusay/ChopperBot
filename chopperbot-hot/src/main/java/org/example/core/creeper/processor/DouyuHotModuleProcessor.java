package org.example.core.creeper.processor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.example.bean.hotmodule.DouyuHotModule;
import org.example.bean.hotmodule.HotModuleList;
import org.example.core.processor.AbstractProcessor;
import us.codecraft.webmagic.Page;


/**
 * @author Genius
 * @date 2023/06/01 22:11
 **/
public class DouyuHotModuleProcessor extends AbstractProcessor {

    @Override
    public void process(Page page) {
        HotModuleList douyuHotModuleList = new HotModuleList();
        try {
            JSONObject data = JSON.parseObject(page.getRawText()).getJSONObject("data");
            JSONArray cateList = data.getJSONArray("cateList");
            JSONObject recommendAndHotObj = cateList.getJSONObject(1);
            JSONArray recommendAndHostList = recommendAndHotObj.getJSONArray("list");

            for (Object elem : recommendAndHostList) {
                if(elem instanceof JSONObject){
                    DouyuHotModule hotModule = new DouyuHotModule(
                            ((JSONObject) elem).getString("tagId"),
                            ((JSONObject) elem).getString("name"),
                            ((JSONObject) elem).getString("url")
                    );
                    douyuHotModuleList.getHotModuleList().add(hotModule);
                }
            }
        }catch (Exception e){
            throw e;
        }
        page.putField("data",douyuHotModuleList);
    }

}
