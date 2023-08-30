package org.example.bean.hotmodule;

import lombok.Data;
import org.example.bean.HotModule;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Genius
 * @date 2023/07/18 21:09
 **/

@Data
public class HotModuleList {
    private List<HotModule> hotModuleList;

    public HotModuleList(){
        hotModuleList = new ArrayList<>();
    }

    public HotModule findHotModule(String hotModuleName){
        for (HotModule hotModule : hotModuleList) {
            if(hotModule.getTagName().equals(hotModuleName)){
                return hotModule;
            }
        }
        return null;
    }

    public HotModule findHotModule(int hotModuleId){
        for (HotModule hotModule : hotModuleList) {
            if(hotModule.getTagId().equals(String.valueOf(hotModuleId))){
                return hotModule;
            }
        }
        return null;
    }

}
