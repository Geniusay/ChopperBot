package org.example.core.auto.video.description;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.constpool.PluginName;
import org.example.core.gpt.ChatGPTMsgBuilder;
import org.example.core.gpt.OpenAPIPlugin;
import org.example.exception.plugin.PluginDependOnException;
import org.example.init.InitPluginRegister;
import org.example.mapper.DescSchemeMapper;
import org.example.mapper.TitleSchemeMapper;
import org.example.plugin.PluginCheckAndDo;
import org.example.pojo.DescScheme;
import org.example.pojo.GPTKey;
import org.example.pojo.TitleScheme;
import org.example.sql.annotation.SQLInit;
import org.example.util.ExceptionUtil;
import org.example.util.MapUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Genius
 * @date 2023/10/25 20:55
 **/
@Component
public class GptDescGenerator extends DescGenerator {

    @Resource
    DescSchemeMapper mapper;
    private List<DescScheme> schemeList;

    public GptDescGenerator() {
        this.schemeList = new ArrayList<>();
    }

    @Override
    @SQLInit(table = "desc_scheme",tableSQL = "CREATE TABLE \"desc_scheme\" (\n" +
            "\t\"id\"\tINTEGER NOT NULL,\n" +
            "\t\"system\"\tTEXT NOT NULL,\n" +
            "\t\"type\"\tTEXT,\n" +
            "\tPRIMARY KEY(\"id\" AUTOINCREMENT)\n" +
            ")",mapper = DescSchemeMapper.class)
    public List<?> sqlInit() {
        return List.of(new DescScheme(null,"作为一个资深的直播运营人员，请你根据我提供给你的直播弹幕内容，标签和主播来叙述一下该段直播发生了什么事情，叙述过程要有趣符合当前短视频快节奏和吸引眼球的风格，生成一个即可，不需要任何标识和说明，字数不超过100字"
                ,"global"));
    }

    @Override
    public void preGenerate() {
        if (!InitPluginRegister.isRegister(PluginName.CHAT_GPT)) {
            throw PluginDependOnException.MissingFatherPlugin(PluginName.CHAT_GPT,"");
        }
        try {
            schemeList = mapper.selectList(new QueryWrapper<>());
        }catch (Exception e){
            throw new RuntimeException("读取desc_scheme表失败");
        }
    }

    @Override
    public String generate(Map<String, Object> data) {
        if (schemeList.isEmpty())return "";
        try {
           return PluginCheckAndDo.CheckAndGet((plugin)->{
                String liver = MapUtil.getString(data,"liver");
               Object barrages = data.get("barrages");
               String barrageStr = "";
               if(barrages instanceof List){
                   barrageStr = OpenAPIPlugin.zipContent(OpenAPIPlugin.zipContent((List) barrages).toString());
               }
                String content = MapUtil.getString(data,"content");
                GPTKey gptKey = ((OpenAPIPlugin) plugin).choseKey(OpenAPIPlugin.APIFunc.CHAT_GPT);
                DescScheme scheme = schemeList.get(0);
                if(gptKey==null)return "";
                ChatGPTMsgBuilder builder = new ChatGPTMsgBuilder().model(gptKey.getModel())
                        .system(scheme.getSystem())
                        .user(String.format("主播：%s\n内容：%s\n直播弹幕：%s", liver,content,barrageStr))
                        .stream(false);
                JSONObject object = ((OpenAPIPlugin) plugin).reqGPT(builder, OpenAPIPlugin.APIFunc.CHAT_GPT);
                return ((OpenAPIPlugin) plugin).getCommonRes(object);
            },PluginName.CHAT_GPT,String.class);
        } catch (Exception e){
            throw new RuntimeException(ExceptionUtil.getCause(e));
        }
    }
}
