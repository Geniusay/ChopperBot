package org.example.core.creeper.processor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.example.bean.Barrage;
import org.example.core.processor.AbstractProcessor;
import org.example.util.TimeUtil;
import us.codecraft.webmagic.Page;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * (B站直播)下载与处理
 * @author 燧枫
 * @date 2023/4/23 18:53
 */
public class BilibiliBarrageLiveProcessor extends AbstractProcessor {


    private String url = "";

    // 弹幕去重
    private Set<String> processedMids;

    // 开始时间戳
    private Long startTime;


    public BilibiliBarrageLiveProcessor(String url){
        this.url = url;
        processedMids = new ConcurrentSkipListSet<>();
    }

    @Override
    public void process(Page page) {
        // 首次加载
        if (isFirst) {
            isFirst = false;
            init(page);
        }
        processOnePage(page);
    }

    // 初始化
    public void init(Page page) {
        // 记录开始爬取时间
        startTime = System.currentTimeMillis();
        page.addTargetRequest(url + "&_=" + System.currentTimeMillis());
    }

    // 处理一页请求
    private void processOnePage(Page page) {

        page.addTargetRequest(url + "&_=" + System.currentTimeMillis());

        // 是否结束爬虫
        if (!isRunning()) {
            return;
        }

        JSONArray roomArray = JSON.parseObject(page.getRawText()).getJSONObject("data").getJSONArray("room");

        // 弹幕列表
        List<Barrage> barrageList = new ArrayList<>();
        for (Object o : roomArray) {
            JSONObject temp = (JSONObject) o;
            JSONObject check_info = temp.getJSONObject("check_info");
            // 唯一id
            String timeLine = temp.getString("timeline");
            String mid = check_info.getString("ct");

            if (!processedMids.contains(mid)) { // 检查mid是否已经存在
                // 真实时间戳
                Long timeReal = TimeUtil.getTimeNaos(timeLine);
                if(timeReal<=startTime)timeReal = startTime;
                // 相对时间戳
                Long timeIndex = timeReal - startTime;
                if (timeIndex < 0) timeIndex = 0L;
                // 弹幕内容
                String content = temp.getString("text");
                Barrage barrage = new Barrage(mid, timeReal, timeIndex, content);
                barrageList.add(barrage);
                processedMids.add(mid); // 将mid添加到已处理集合中
            }
        }

        // 发送给pipeline
        page.putField("barrageList", barrageList);


    }
}
