package org.example.barrage;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.summary.TextRankKeyword;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author welsir
 * @Date 2023/9/14 19:48
 */
public class KeywordDdaptationTest {

    public static void main(String[] args) {
        String text = "？？？？？";

        // 使用TextRank算法提取关键词
        List<String> keywords = TextRankKeyword.getKeywordList(text, 5);

        // 对文本进行分词
        List<Term> termList = HanLP.segment(text);
        System.out.println("分词结果：" + termList);
        System.out.println(NLPTokenizer.segment("我新造一个词叫幻想乡你能识别并标注正确词性吗？"));
        // 注意观察下面两个“希望”的词性、两个“晚霞”的词性
        System.out.println(NLPTokenizer.analyze("我的希望是希望张晚霞的背影被晚霞映红").translateLabels());
        System.out.println(NLPTokenizer.analyze("支援臺灣正體香港繁體：微软公司於1975年由比爾·蓋茲和保羅·艾倫創立。"));
    }


}
