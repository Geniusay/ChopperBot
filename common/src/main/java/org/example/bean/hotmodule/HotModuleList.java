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

}
