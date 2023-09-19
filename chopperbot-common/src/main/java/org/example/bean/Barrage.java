package org.example.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import lombok.Data;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

/**
 * 弹幕通用类
 * @author 燧枫
 * @date 2023/4/23 15:28
*/
@Data
public class Barrage implements Serializable,Comparable<Barrage> {

    // 弹幕唯一id
    private String mid;

    // 真实时间戳
    private Long timeReal;

    // 相对于视频的时间戳
    private Long timeIndex;

    // 弹幕内容
    private String content;

    public Barrage(){

    }

    public Barrage(String mid, Long timeReal, Long timeIndex, String content) {
        this.mid = mid;
        this.timeReal = timeReal;
        this.timeIndex = timeIndex;
        this.content = content;
    }

    /**
     * @description: use to set BarrageProperty
     */
    public static List<Barrage> copyProperty(JSONArray BarrageList){
        return JSON.parseArray(BarrageList.toJSONString(), Barrage.class);
    }

    @Override
    public int compareTo(Barrage o) {
        return (int) (this.getTimeReal()-o.getTimeReal());
    }


}
