package org.example.pojo.record;

import lombok.Data;

/**
 * 单场录播
 * @author 燧枫
 * @date 2023/8/2 12:47
 */
@Data
public class RecordEntry {

    // 主播名
    private String author;

    // 录播的hashId
    private String hashId;

    // show_id
    private String showId;

    // 标题
    private String title;

    // 场次
    private String showRemark;

    // 时长
    private String duration;

    // 封面
    private String cover;

    // 观看人次
    private int viewNum;
}
