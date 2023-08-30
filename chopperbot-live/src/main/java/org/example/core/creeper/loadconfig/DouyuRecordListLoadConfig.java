package org.example.core.creeper.loadconfig;

import org.example.core.loadconfig.LoadConfig;

import java.time.LocalDate;

/**
 * @author Genius
 * @date 2023/08/19 01:11
 **/
public class DouyuRecordListLoadConfig extends LoadConfig {

    private static final String DOUYU_RECORD_LIST = "https://v.douyu.com/wgapi/vod/center/authorShowVideoList?page=%S&limit=20&up_id=%s";

    // 斗鱼指定天的录播列表
    private static final String DOUYU_RECORD_DAY_LIST = "https://v.douyu.com/wgapi/vod/center/getShowReplayList?vid=%s&up_id=%s";

    private int index;

    private String upId;

    private LocalDate date;

    public DouyuRecordListLoadConfig(int index, String upId) {
        this.index = index;
        this.upId = upId;
    }

    public DouyuRecordListLoadConfig(int index, String upId, LocalDate date) {
        this.index = index;
        this.upId = upId;
        this.date = date;
    }

    public String getDouyuRecordDayList() {
        return DOUYU_RECORD_DAY_LIST;
    }

    public String getDouyuRecordList() {
        return String.format(DOUYU_RECORD_LIST,index,upId);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getUpId() {
        return upId;
    }

    public void setUpId(String upId) {
        this.upId = upId;
    }

    public LocalDate getDate() {
        return date;
    }
}
