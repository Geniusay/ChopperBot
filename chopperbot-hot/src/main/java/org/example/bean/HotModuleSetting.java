package org.example.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 热门模块的设置类
 * @author Genius
 * @date 2023/07/20 00:25
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("hot_module_setting")
public class HotModuleSetting {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String platform;         //平台

    private Integer failRetryTimes;      //失败重试次数

    private Boolean enableHotModule; //是否开启热门模块爬取

    private Boolean enableHotLive;   //是否开启热门直播爬取

    private Boolean followDogEnable; //当开启autoWork时，启用跟风狗模式，跟风狗模式会自动爬取热门直播

    private Long updateHotModuleTimes; //自动更新平台热门模块时间

    private Long updateHotLivesTimes; //自动更新平台热门直播时间

}
