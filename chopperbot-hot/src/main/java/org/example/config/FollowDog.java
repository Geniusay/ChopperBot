package org.example.config;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * config中的跟风狗设置，用于筛选和推荐某个模块的前几个主播
 * @author Genius
 * @date 2023/07/20 00:40
 **/

//
@Data
@AllArgsConstructor
public class FollowDog {
    public final static String ALL_LIVES = "all";
    private String moduleName;  //模块名称，all代表热门直播
    private int top; //前几个
    private List<String> banLiver; //去除的主播，可以是正则

    @Override
    public String toString() {
        return "FollowDog{" +
                "moduleName='" + moduleName + '\'' +
                ", top=" + top +
                ", banLiver=" + banLiver +
                '}';
    }
}
