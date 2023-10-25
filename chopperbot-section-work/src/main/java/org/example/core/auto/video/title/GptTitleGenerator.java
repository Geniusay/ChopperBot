package org.example.core.auto.video.title;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.constpool.PluginName;
import org.example.core.auto.SectionPipeline;
import org.example.core.gpt.ChatGPTMsgBuilder;
import org.example.core.gpt.OpenAPIPlugin;
import org.example.core.label.LabelManagerPlugin;
import org.example.exception.plugin.PluginDependOnException;
import org.example.init.InitPluginRegister;
import org.example.mapper.TitleSchemeMapper;
import org.example.plugin.PluginCheckAndDo;
import org.example.pojo.GPTKey;
import org.example.pojo.TitleScheme;
import org.example.sql.SQLInitMachine;
import org.example.sql.annotation.SQLInit;
import org.example.util.ClassUtil;
import org.example.util.ExceptionUtil;
import org.example.util.MapUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Genius
 * @date 2023/10/22 18:03
 **/
@Component
public class GptTitleGenerator extends TitleGenerator{

    @Resource
    TitleSchemeMapper mapper;
    private List<TitleScheme> schemeList;

    public GptTitleGenerator() {
        this.schemeList = new ArrayList<>();
    }

    @Override
    public String generate(Map<String,Object> data) {
        if (schemeList.isEmpty())return "";
        try {
            return PluginCheckAndDo.CheckAndGet((plugin)->{
                Object barrages = data.get("barrages");
                String barrageStr = "";
                if(barrages instanceof List){
                    barrageStr = OpenAPIPlugin.zipContent(OpenAPIPlugin.zipContent((List) barrages).toString());
                }
                String content = MapUtil.getString(data,"content");
                String liver = MapUtil.getString(data,"liver");
                String labels = MapUtil.getString(data,"labels");
                GPTKey gptKey = ((OpenAPIPlugin) plugin).choseKey(OpenAPIPlugin.APIFunc.CHAT_GPT);
                TitleScheme scheme = schemeList.get(0);
                if(gptKey==null)return "";
                ChatGPTMsgBuilder builder = new ChatGPTMsgBuilder().model(gptKey.getModel())
                        .system(scheme.getSystem())
                        .user(String.format("主播：%s\n内容：%s\n类型：%s\n弹幕：%s\n", liver,content,labels,barrageStr))
                        .stream(false);
                JSONObject object = ((OpenAPIPlugin) plugin).reqGPT(builder, OpenAPIPlugin.APIFunc.CHAT_GPT);
                return ((OpenAPIPlugin) plugin).getCommonRes(object);
            },PluginName.CHAT_GPT,String.class);
        } catch (Exception e){
            throw new RuntimeException(ExceptionUtil.getCause(e));
        }
    }

    @Override
    public void preGenerate() {
        if (!InitPluginRegister.isRegister(PluginName.CHAT_GPT)) {
            throw PluginDependOnException.MissingFatherPlugin(PluginName.CHAT_GPT,"");
        }
        try {
            schemeList = mapper.selectList(new QueryWrapper<>());
        }catch (Exception e){
            throw new RuntimeException("读取title_scheme表失败");
        }
    }

    @Override
    @SQLInit(table = "title_scheme",tableSQL = "CREATE TABLE \"title_scheme\" (\n" +
            "\t\"id\"\tINTEGER NOT NULL,\n" +
            "\t\"system\"\tTEXT NOT NULL,\n" +
            "\t\"type\"\tTEXT,\n" +
            "\tPRIMARY KEY(\"id\" AUTOINCREMENT)\n" +
            ")",mapper = TitleSchemeMapper.class)
    public List<?> sqlInit() {
        return List.of(new TitleScheme(null,"标题一:主播被搞到破防崩溃，上演大型红温现场，这次生气不像演的感觉要掉小珍珠了！\\n标题二:主播节食减肥高光时刻回顾，饭后猛炫6只去皮鸡腿！ \\n标题三:BLG击败FNC！外战一把不输！外战幻神BLG！武器神Bin天降晕四个斩获三杀！ \\n标题四:前有TheShy天神下凡一锤四，今有刚神下凡一斩四，海龟剑姬jjking荣归故里吊打小代！\\n标题五:属于男生的综艺来了！姿态最新惩罚局，一手辅助维克托上演“超鬼”。请你学习以上标题样例总结出一种风格，我接下来为您提供的主播名称，弹幕内容，直播内容，标签等信息，请你作为一个热门短视频博主，根据你总结的风格和我提供的信息来生成一个吸引眼球，爆火，符合当今流行梗的短视频标题。注意：只需要给我标题，不需要“标题：”等任何前缀词语。你的所有数据信息应该从我为你提供的信息中获取，不要从标题样例中获取任何数据信息。"
                ,"global"));
    }
}
