package org.example.core.analysis;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Data;
import org.example.bean.Barrage;
import org.example.core.gpt.ChatGPTMsgBuilder;
import org.example.core.gpt.ChatGPTPlugin;
import org.example.mapper.AnalysisSchemeMapper;
import org.example.plugin.SpringBootPlugin;
import org.example.pojo.AnalysisScheme;
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

            ChatGPTMsgBuilder builder = new ChatGPTMsgBuilder().model(chatGPTPlugin.getKey().getModel())
                    .system(this.scheme.getSystem())
                    .user("弹幕：" + barrage)
                    .stream(false);

            JSONObject object = chatGPTPlugin.reqGPT(builder);

//            System.out.println(object.getJSONArray("choices").getJSONObject(0).getJSONObject("message").getString("content"));

            Pattern pattern = Pattern.compile("\\[(.*?)]");

            Matcher matcher = pattern.matcher(object.getJSONArray("choices").getJSONObject(0).getJSONObject("message").getString("content"));

            if (matcher.find()) return matcher.group(1);
        } catch (Exception e){
            throw new RuntimeException("analysis failed!please check or try again!");
        }

        return "";
    }
}
