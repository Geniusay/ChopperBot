package org.example.core.auto.video.title;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.constpool.PluginName;
import org.example.exception.plugin.PluginDependOnException;
import org.example.exception.plugin.PluginNotRegisterException;
import org.example.init.InitPluginRegister;
import org.example.mapper.TitleSchemeMapper;
import org.example.pojo.TitleScheme;
import org.example.sql.annotation.SQLInit;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Genius
 * @date 2023/10/22 18:03
 **/
@Component
public class GptTitleGenerator implements TitleGenerator{

    @Resource
    TitleSchemeMapper titleSchemeMapper;
    private List<TitleScheme> schemeList;

    public GptTitleGenerator() {
        this.schemeList = new ArrayList<>();
    }

    @Override
    public String generate(Object data) {
        return null;
    }

    @Override
    @SQLInit(table = "title_scheme",tableSQL = "CREATE TABLE \"title_scheme\" (\n" +
            "\t\"id\"\tINTEGER NOT NULL,\n" +
            "\t\"system\"\tTEXT NOT NULL,\n" +
            "\t\"type\"\tTEXT,\n" +
            "\tPRIMARY KEY(\"id\" AUTOINCREMENT)\n" +
            ")",mapper = TitleSchemeMapper.class)
    public Object preGenerate() {
        if (!InitPluginRegister.isRegister(PluginName.CHAT_GPT)) {
            throw PluginDependOnException.MissingFatherPlugin(PluginName.CHAT_GPT,"");
        }
        try {
            schemeList = titleSchemeMapper.selectList(new QueryWrapper<>());
        }catch (Exception e){
            throw new RuntimeException("读取title_scheme表失败");
        }
        return List.of(new TitleScheme(null,"请你学习以下标题风格，并对这种风格做出总结。命名为风格A\n" +
                "标题一:姿态被搞到破防崩溃，上演大型红温现场，这次生气不像演的感觉要掉小珍珠了！\n" +
                "标题二:PDD节食减肥高光时刻回顾，饭后猛炫6只去皮鸡腿！ \n" +
                "标题三:BLG击败FNC！外战一把不输！外战幻神BLG！武器神Bin天降晕四个斩获三杀！ \n" +
                "标题四:前有TheShy天神下凡一锤四，今有刚神下凡一斩四，海龟剑姬jjking荣归故里吊打小代！\n" +
                "标题五:属于男生的综艺来了！姿态最新惩罚局，一手辅助维克托上演“超鬼”"
                ,"global"));
    }
}
