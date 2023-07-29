package org.example.config;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 热门模块的设置类
 * @author Genius
 * @date 2023/07/20 00:25
 **/
@Data
@AllArgsConstructor
public class HotModuleSetting {

    private String platform;         //平台

    private int failRetryTimes;      //失败重试次数

    private boolean enableHotModule; //是否开启热门模块爬取

    private boolean enableHotLive;   //是否开启热门直播爬取

    private boolean autoWork;        //是否自动进行主播直播下载任务推送

    private List<String> focusLiver; //关注的主播

    private boolean followDogEnable; //当开启autoWork时，启用跟风狗模式，跟风狗模式会自动爬取热门直播

    private List<FollowDog> followDogs;

    private long updateHotModuleTimes; //自动更新平台热门模块时间

    private long updateHotLivesTimes; //自动更新平台热门直播时间

}
