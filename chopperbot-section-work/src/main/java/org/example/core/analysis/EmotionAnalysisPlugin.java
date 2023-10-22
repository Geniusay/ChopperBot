package org.example.core.analysis;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Data;
import org.example.bean.Barrage;
import org.example.constpool.PluginName;
import org.example.core.gpt.ChatGPTMsgBuilder;
import org.example.core.gpt.ChatGPTPlugin;
import org.example.core.label.LabelManagerPlugin;
import org.example.init.InitPluginRegister;
import org.example.mapper.AnalysisSchemeMapper;
import org.example.plugin.SpringBootPlugin;
import org.example.pojo.AnalysisScheme;
import org.example.pojo.VideoLabel;
import org.example.sql.annotation.SQLInit;
import org.example.util.ExceptionUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @Date 2023/10/16
 * @Author xiaochun
 */
@Data
@Component
public class EmotionAnalysisPlugin extends SpringBootPlugin {

    @Resource
    AnalysisSchemeMapper mapper;

    @Resource
    ChatGPTPlugin chatGPTPlugin;

    String barrage;

    AnalysisScheme scheme;

    @Override
    public boolean init(){
        try {
            chooseScheme();
        } catch (Exception e){
            throw new RuntimeException("init analysis failed!pleas check or try again!");
        }
        return super.init();
    }

    private void chooseScheme(){
        List<AnalysisScheme> schemes = mapper.selectList(new QueryWrapper<>());
        if(schemes == null || schemes.isEmpty()) throw new RuntimeException("invaild anlysis scheme!please set scheme!");
        scheme = schemes.get(0);
    }

    public String analysis(AnalysisSchemeBuilder analysisSchemeBuilder){
        return analysis(analysisSchemeBuilder.getBarrages(), analysisSchemeBuilder.getScheme());
    }

    public String analysis(List<Barrage> barrages, AnalysisScheme scheme){
        if(scheme != null) this.scheme = scheme;
        return analysis(barrages);
    }

    public String analysis(List<Barrage> barrages){
        try {
            barrage = List.of(barrages.stream().map(Barrage::getContent).collect(Collectors.toList())).toString();
            List<String> list = InitPluginRegister.getPlugin(PluginName.LABEL_MANAGER, LabelManagerPlugin.class).labelStrList();
            ChatGPTMsgBuilder builder = new ChatGPTMsgBuilder().model(chatGPTPlugin.getKey().getModel())
                    .system(this.scheme.getSystem(list.toString()))
                    .user("弹幕：" + barrage)
                    .stream(false);

            JSONObject object = chatGPTPlugin.reqGPT(builder);

            return chatGPTPlugin.getCommonRes(object);
        } catch (Exception e){
            this.error("标题生成失败", String.format("标题生成失败,原因:%s", ExceptionUtil.getCause(e)),true);
            return "";
        }
    }

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
}
