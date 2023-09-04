package org.example.pojo.record;

import lombok.Data;

import java.util.List;

/**
 * 单天录播
 * @author 燧枫
 * @date 2023/8/1 22:09
 */
@Data
public class RecordDayEntry {

    // 主播名
    private String author;

    // 录播的hashId
    private String hashId;

    // 录播的标题
    private String title;

    // 录播的日期时间
    private String date;

    // 当天的列表
    private List<RecordEntry> recordEntryList;
}
