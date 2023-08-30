package org.example.core.creeper.processor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.example.bean.barrage.DouyuBarrage;
import org.example.core.processor.AbstractProcessor;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.utils.HttpConstant;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * (斗鱼录播)下载与处理
 *
 * @author 燧枫
 * @date 2023/4/23 18:54
 */
public class DouyuBarrageRecordProcessor extends AbstractProcessor {


    private AtomicInteger count = new AtomicInteger(0);

    private int skipCount = 0;

    private String urlPrefix;

    // 前缀url

    public DouyuBarrageRecordProcessor(String url) {
        this.urlPrefix = url;
    }

    @Override
    public void process(Page page) {
        // 首次加载
        if (isFirst) {
            init(page);
            isFirst = false;
        }
        processOnePage(page);
    }

    // 初始化
    public void init(Page page) {
        // 拼装前缀url
        urlPrefix += "&forward=0&offset=";
        // 首页请求加入队列
        sendOnePage(page, "-1");
    }

    // 处理一页请求
    private void processOnePage(Page page) {

        // 是否结束爬虫
        if (!isRunning.get()) {
            return;
        }

        JSONObject data = JSON.parseObject(page.getRawText()).getJSONObject("data");
        // 前一页id
        String preId = data.getString("pre");
        // 如果有,先将前一页数据加入队列中
        if (!preId.equals("-1")) {
            sendOnePage(page, preId);
        }
        // 处理弹幕列表
        JSONArray barrageArray = data.getJSONArray("list");
        List<DouyuBarrage> barrageList = new ArrayList<>();
        for (Object o : barrageArray) {
            JSONObject temp = (JSONObject) o;
            // 唯一id
            String mid = temp.getString("mid");
            // 真实时间戳
            Long timeReal = temp.getLong("sts");
            // 相对时间戳
            Long timeIndex = temp.getLong("tl") / 1000;
            // 弹幕内容
            String content = temp.getString("ctt");
            DouyuBarrage barrage = new DouyuBarrage(mid, timeReal, timeIndex, content);
            barrageList.add(barrage);
        }
        // 发送给pipeline
        page.putField("barrageList", barrageList);

        if(preId.equals("-1")){
            end();
        }
    }

    // 根据id发送一页的请求
    private void sendOnePage(Page page, String id) {
        Request request = new Request(urlPrefix + id);
        request.setMethod(HttpConstant.Method.GET);
        page.addTargetRequest(request);
    }
}
