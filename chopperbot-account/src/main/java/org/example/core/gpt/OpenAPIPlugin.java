package org.example.core.gpt;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Data;
import okhttp3.*;
import org.example.core.msgbuilder.MsgBuilder;
import org.example.http.OkHttpAgent;
import org.example.mapper.GPTKeyMapper;
import org.example.plugin.SpringBootPlugin;
import org.example.pojo.GPTKey;
import org.example.sql.annotation.SQLInit;
import org.example.util.ExceptionUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @Date 2023/10/12
 * @Author xiaochun
 */
@Data
@Component
public class OpenAPIPlugin extends SpringBootPlugin {

    public enum APIFunc{
        CHAT_GPT("chatgpt"),

        WHISPER("whisper");

        private String name;
        APIFunc(String name){
            this.name = name;
        }

        public String funcName(){
            return this.name;
        }
    }
    @Resource
    GPTKeyMapper mapper;

    @Resource
    OkHttpAgent okHttpAgent;


    @Override
    public boolean init(){
       return super.init();
    }

    public JSONObject reqGPT(MsgBuilder builder,APIFunc func){
        return reqGPT(builder.done(),func);
    }

    public JSONObject reqGPT(String msg,APIFunc func){
        return reqGPT(buildBody(msg),func);
    }

    public JSONObject reqGPT(RequestBody body,APIFunc func){
        OkHttpClient client = okHttpAgent.agentClient();
        Request request = buildReq(body,func);
        try (Response response = client.newCall(request).execute()){
            if (response.body() != null) {
                String content = response.body().string();
                if(content.contains("error")){
                    this.error("OpenAI API 调用错误",String.format("OpenAI API 调用错误，原因：%s", content),true);
                }
                return JSONObject.parseObject(content);
            }
        } catch (IOException e) {
            this.error(String.format("Error: api request fail,Cause:%s", ExceptionUtil.getCause(e)));
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
    private Request buildReq(RequestBody body,APIFunc func){
        GPTKey gptKey = choseKey(func);
        return new Request.Builder()
                .url(gptKey.getUrl())
                .post(body)
                .headers(buildHeader(gptKey.getKey()))
                .build();
    }

    public GPTKey choseKey(APIFunc func){
        GPTKey gptKey = mapper.selectOne(new QueryWrapper<GPTKey>().eq("function",func.funcName()));
        if(gptKey==null)throw new RuntimeException("invalid gpt key!please set your key!");
        return gptKey;
    }

    private Headers buildHeader(String key){
        return new Headers.Builder()
                .add("content-type", "application/json")
                .add("scheme","https")
                .add("Authorization", "Bearer " + key)
                .build();
    }

    public List<String> funcList(){
        return Arrays.stream(APIFunc.values()).map(APIFunc::funcName).collect(Collectors.toList());
    }

    @Override
    @SQLInit(table = "gpt_key",tableSQL = "CREATE TABLE \"gpt_key\" (\n" +
            "\t\"key\"\tTEXT NOT NULL,\n" +
            "\t\"url\"\tTEXT NOT NULL,\n" +
            "\t\"model\"\tTEXT NOT NULL,\n" +
            "\t\"function\"\tTEXT NOT NULL DEFAULT 'chatgpt' UNIQUE\n" +
            ")",mapper = GPTKeyMapper.class)
    public List<GPTKey> sqlInit() {
        return List.of(new GPTKey("sk-xgUDtOdRgQLigz2D0e4cA665441e4287AfCf8458B1C21b0f","https://oneapi.a9.gay/v1/chat/completions","gpt-3.5-turbo",APIFunc.CHAT_GPT.funcName()));
    }
}
