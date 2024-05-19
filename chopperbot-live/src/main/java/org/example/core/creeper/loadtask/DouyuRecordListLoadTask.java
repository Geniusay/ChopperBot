package org.example.core.creeper.loadtask;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.example.core.creeper.loadconfig.DouyuRecordListLoadConfig;
import org.example.core.loadconfig.LoadConfig;
import org.example.core.loadtask.CommonLoadTask;
import org.example.core.loadtask.LoadTask;
import org.example.pojo.record.RecordDayEntry;
import org.example.pojo.record.RecordEntry;
import org.example.pojo.record.RecordList;
import org.example.util.HttpClientUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Genius
 * @date 2023/08/21 00:58
 **/
public class DouyuRecordListLoadTask extends CommonLoadTask<RecordList> {


    DouyuRecordListLoadConfig douyuRecordListLoadConfig;

    public DouyuRecordListLoadTask(DouyuRecordListLoadConfig loadConfig) {
        super(loadConfig);
        douyuRecordListLoadConfig = loadConfig;
    }

    @Override
    public RecordList start() {
        int index = douyuRecordListLoadConfig.getIndex();
        LocalDate date = douyuRecordListLoadConfig.getDate();
        String upId = douyuRecordListLoadConfig.getUpId();
        String url = douyuRecordListLoadConfig.getDouyuRecordList();

        JSONObject data = JSON.parseObject(HttpClientUtil.get(url)).getJSONObject("data");
        JSONArray recordListArray = data.getJSONArray("list");
        int count = data.getInteger("count");

        List<RecordDayEntry> recordDayEntryList = new ArrayList<>();
        for (Object o : recordListArray) {
            // 更新每天的数据
            JSONObject enter = (JSONObject) o;
            RecordDayEntry recordDayEntry = new RecordDayEntry();
            if(date!=null&&!enter.get("time").toString().contains(date.toString())) continue;

            recordDayEntry.setTitle(enter.getString("title"));
            JSONObject temp = (JSONObject) enter.getJSONArray("video_list").get(0);
            recordDayEntry.setAuthor(temp.getString("author"));
            String hash_id = temp.getString("hash_id");
            recordDayEntry.setHashId(hash_id);
            // 再更新单场的列表
            List<RecordEntry> recordEntryList = new ArrayList<>();
            String day_url = String.format(douyuRecordListLoadConfig.getDouyuRecordDayList(), hash_id, upId);
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
        }
        RecordList recordList = new RecordList();
        recordList.setRecordDayCount(count);
        recordList.setRecordDayEntryList(recordDayEntryList);
        return recordList;
    }

    @Override
    public void end() {

    }


}
