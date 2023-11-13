package org.example.core.auto.video.label;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.constpool.PluginName;
import org.example.core.gpt.ChatGPTMsgBuilder;
import org.example.core.gpt.OpenAPIPlugin;
import org.example.exception.plugin.PluginDependOnException;
import org.example.init.InitPluginRegister;
import org.example.mapper.AnalysisSchemeMapper;
import org.example.plugin.PluginCheckAndDo;
import org.example.pojo.AnalysisScheme;
import org.example.pojo.DescScheme;
import org.example.pojo.GPTKey;
import org.example.sql.annotation.SQLInit;
import org.example.util.ExceptionUtil;
import org.example.util.MapUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class GptLabelGenerator extends LabelGenerator{

    @Resource
    AnalysisSchemeMapper mapper;

    private List<AnalysisScheme> schemes;

    @Override
    @SQLInit(table = "analysis_scheme",tableSQL = "CREATE TABLE \"analysis_scheme\" (\n" +
            "  \"id\" integer NOT NULL,\n" +
            "  \"system\" text NOT NULL,\n" +
            "  \"comment\" TEXT,\n" +
            "  PRIMARY KEY (\"id\")\n" +
            ")",mapper = AnalysisSchemeMapper.class)
    public List<AnalysisScheme> sqlInit() {
        return List.of(new AnalysisScheme(null,
                "请你作为一个专门看直播的观众，对下列的观众发送的弹幕内容进行分析，然后根据弹幕内容返回从以下几个标签返回给我最合适的一个标签来形容这段内容",
                "方案一"));
    }

    @Override
    public void preGenerate() {
        if (!InitPluginRegister.isRegister(PluginName.CHAT_GPT)) {
            throw PluginDependOnException.MissingFatherPlugin(PluginName.CHAT_GPT,"");
        }
        try {
            schemes = mapper.selectList(new QueryWrapper<>());
        }catch (Exception e){
            throw new RuntimeException("读取label_scheme表失败");
        }
    }

    @Override
    public List<String> generate(Map<String, Object> data) {
        if (schemes.isEmpty())return new ArrayList<>();
        try {
            return PluginCheckAndDo.CheckAndGet((plugin)->{
                Object barrages = data.get("barrages");
                String barrageStr = "";
                if(barrages instanceof List){
                    barrageStr = OpenAPIPlugin.zipContent(OpenAPIPlugin.zipContent((List) barrages).toString());
                }
                GPTKey gptKey = ((OpenAPIPlugin) plugin).choseKey(OpenAPIPlugin.APIFunc.CHAT_GPT);
                AnalysisScheme scheme = schemes.get(0);
                if(gptKey==null)return new ArrayList<>();
                ChatGPTMsgBuilder builder = new ChatGPTMsgBuilder().model(gptKey.getModel())
                        .system(scheme.getSystem())
                        .user("弹幕：" + barrageStr)
                        .stream(false);
                String liver = MapUtil.getString(data,"liver");
                String platform = MapUtil.getString(data,"platform");
                String tag = MapUtil.getString(data,"tag");
                JSONObject object = ((OpenAPIPlugin) plugin).reqGPT(builder, OpenAPIPlugin.APIFunc.CHAT_GPT);
                return List.of(((OpenAPIPlugin) plugin).getCommonRes(object),liver,platform,tag);
            },PluginName.CHAT_GPT,List.class);
        } catch (Exception e){
            throw new RuntimeException(ExceptionUtil.getCause(e));
        }
    }
}
