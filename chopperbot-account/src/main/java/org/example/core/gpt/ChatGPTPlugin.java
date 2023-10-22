package org.example.core.gpt;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Data;
import okhttp3.*;
import org.example.core.msgbuilder.MsgBuilder;
import org.example.mapper.GPTKeyMapper;
import org.example.plugin.SpringBootPlugin;
import org.example.pojo.GPTKey;
import org.example.sql.annotation.SQLInit;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Date 2023/10/12
 * @Author xiaochun
 */
@Data
@Component
public class ChatGPTPlugin extends SpringBootPlugin {

    @Resource
    GPTKeyMapper mapper;

    private GPTKey key;

    private Headers header;

    @Override
    public boolean init(){
        choseKey();
        buildHeader(key.getKey());
        return super.init();
    }

    public JSONObject reqGPT(MsgBuilder builder){
        return reqGPT(builder.done());
    }

    public JSONObject reqGPT(String msg){

        OkHttpClient client = new OkHttpClient();
        Request request = buildReq(buildBody(msg));
        try (Response response = client.newCall(request).execute()){
            if (response.body() != null) {
                String content = response.body().string();
                return JSONObject.parseObject(content);
            }
        } catch (IOException e) {
            this.error(String.format("Error: gpt request fail,Cause:%s", e.getCause()));
        }
        return null;
    }

    public String getCommonRes(JSONObject resp){
        Pattern pattern = Pattern.compile("\\[(.*?)]");

        Matcher matcher = pattern.matcher(resp.getJSONArray("choices").getJSONObject(0).getJSONObject("message").getString("content"));

        if (matcher.find()) return matcher.group(1);
        return "";
    }

    private RequestBody buildBody(String msg){
        return  RequestBody.create(msg, MediaType.parse("application/json"));
    }
    private Request buildReq(RequestBody body){
        return new Request.Builder()
                .url(key.getUrl())
                .post(body)
                .headers(header)
                .build();
    }

    private void choseKey(){
        List<GPTKey> gptKeys = mapper.selectList(new QueryWrapper<>());
        if(gptKeys==null||gptKeys.isEmpty())throw new RuntimeException("invalid gpt key!please set your key!");
        key = gptKeys.get(0);
    }

    private void buildHeader(String key){
        this.header = new Headers.Builder()
                .add("content-type", "application/json")
                .add("Authorization", "Bearer " + key)
                .build();
    }

    @Override
    @SQLInit(table = "gpt_key",tableSQL = "CREATE TABLE \"gpt_key\" (\n" +
            "  \"key\" TEXT NOT NULL,\n" +
            "  \"url\" TEXT NOT NULL,\n" +
            "  \"model\" TEXT NOT NULL\n" +
            ")",mapper = GPTKeyMapper.class)
    public List<GPTKey> sqlInit() {
        return List.of(new GPTKey("sk-xgUDtOdRgQLigz2D0e4cA665441e4287AfCf8458B1C21b0f"
                ,"https://oneapi.a9.gay/v1/chat/completions"
                ,"gpt-3.5-turbo"));
    }
}
