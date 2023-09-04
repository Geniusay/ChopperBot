package org.example.pojo.record;

import lombok.Data;

import java.util.List;

/**
 * 录播列表
 * @author 燧枫
 * @date 2023/8/1 21:59
 */
@Data
public class RecordList {

    // 主播录播总天数
    private int recordDayCount = 0;

    // 录播列表
    private List<RecordDayEntry> recordDayEntryList;
}
