package org.example.core.component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.example.pojo.record.RecordDayEntry;
import org.example.pojo.record.RecordEntry;
import org.example.pojo.record.RecordList;
import org.example.utils.HttpClientUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 简单请求
 * @author 燧枫
 * @date 2023/8/1 22:19
 */
public class SimpleRequest {

    // 斗鱼录播列表请求url
    private static String DOUYU_RECORD_LIST = "https://v.douyu.com/wgapi/vod/center/authorShowVideoList?page=%S&limit=20&up_id=%s";

    // 斗鱼指定天的录播列表
    private static String DOUYU_RECORD_DAY_LIST = "https://v.douyu.com/wgapi/vod/center/getShowReplayList?vid=%s&up_id=%s";

    // 指定页数获取录播列表
    public static RecordList getRecordList(String upId, int index) {
        String url = String.format(DOUYU_RECORD_LIST, index, upId);
        JSONObject data = JSON.parseObject(HttpClientUtil.get(url)).getJSONObject("data");
        JSONArray recordListArray = data.getJSONArray("list");
        int count = data.getInteger("count");
        List<RecordDayEntry> recordDayEntryList = new ArrayList<>();
        recordListArray.forEach(o -> {
            // 更新每天的数据
            JSONObject enter = (JSONObject) o;
            RecordDayEntry recordDayEntry = new RecordDayEntry();
            recordDayEntry.setTitle(enter.getString("title"));
            JSONObject temp = (JSONObject) enter.getJSONArray("video_list").get(0);
            recordDayEntry.setAuthor(temp.getString("author"));
            String hash_id = temp.getString("hash_id");
            recordDayEntry.setHashId(hash_id);
            // 再更新单场的列表
            List<RecordEntry> recordEntryList = new ArrayList<>();
            String day_url = String.format(DOUYU_RECORD_DAY_LIST, hash_id, upId);
            JSONObject day = JSON.parseObject(HttpClientUtil.get(day_url)).getJSONObject("data");
            recordDayEntry.setDate(day.getString("date"));

            JSONArray recordDayListArray = day.getJSONArray("list");
            recordDayListArray.forEach(single -> {
                JSONObject one = (JSONObject) single;
                RecordEntry recordEntry = new RecordEntry();
                recordEntry.setDuration(one.getString("video_duration"));
                recordEntry.setViewNum(one.getInteger("view_num"));
                recordEntry.setCover(one.getString("cover"));
                recordEntry.setShowRemark(one.getString("show_remark"));
                recordEntry.setShowId(one.getString("show_id"));
                recordEntry.setAuthor(one.getString("nickname"));
                recordEntry.setTitle(one.getString("title"));
                recordEntry.setHashId(one.getString("hash_id"));
                recordEntryList.add(recordEntry);
            });
            recordDayEntry.setRecordEntryList(recordEntryList);
            recordDayEntryList.add(recordDayEntry);
        });
        RecordList recordList = new RecordList();
        recordList.setRecordDayCount(count);
        recordList.setRecordDayEntryList(recordDayEntryList);
        return recordList;
    }


}
