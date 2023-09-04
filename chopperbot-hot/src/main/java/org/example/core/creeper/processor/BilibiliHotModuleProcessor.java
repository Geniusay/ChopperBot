package org.example.core.creeper.processor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.example.bean.hotmodule.BilibiliHotModule;
import org.example.bean.hotmodule.DouyuHotModule;
import org.example.bean.hotmodule.HotModuleList;
import org.example.core.processor.AbstractProcessor;
import us.codecraft.webmagic.Page;

/**
 * @author Genius
 * @date 2023/06/01 22:10
 **/
public class BilibiliHotModuleProcessor extends AbstractProcessor {


    @Override
    public void process(Page page) {
        HotModuleList bilibiliHotModuleList = new HotModuleList();
        try {
            JSONObject data = JSON.parseObject(page.getRawText()).getJSONObject("data");
            JSONArray parentList = data.getJSONArray("data");
            for (Object parent : parentList) {
                if(parent instanceof JSONObject){
                    JSONArray moduleList = ((JSONObject)parent).getJSONArray("list");
                    for (Object module : moduleList) {
                        if(module instanceof JSONObject){
                            JSONObject temp = (JSONObject) module;
                            BilibiliHotModule hotModule = new  BilibiliHotModule(
                                    temp.getString("id"),
                                    temp.getString("name"),
                                    temp.getString("parent_id"),
                                    temp.getString("parent_name"),
                                    temp.getString("act_id"),
                                    temp.getString("pic"),
                                    temp.getInteger("area_type")
                            );
                            bilibiliHotModuleList.getHotModuleList().add(hotModule);
                        }
                    }
                }
            }
        }catch (Exception e){
            throw e;
        }
        page.putField("data",bilibiliHotModuleList);
    }

}
