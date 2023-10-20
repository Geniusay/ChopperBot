package org.example.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * config中的跟风狗设置，用于筛选和推荐某个模块的前几个主播
 * @author Genius
 * @date 2023/07/20 00:40
 **/

//
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("follow_dog")
public class FollowDog {
    public final static String ALL_LIVES = "all";

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String dogId;
    private String platform;
    private String moduleName;  //模块名称，all代表热门直播
    private Integer top; //前几个
    private String banLiver; //去除的主播，可以是正则

}
