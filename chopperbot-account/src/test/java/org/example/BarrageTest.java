package org.example;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.model.crf.CRFLexicalAnalyzer;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @Description
 * @Author welsir
 * @Date 2023/9/18 8:08
 */
public class BarrageTest {

    @Test
    public void test() throws IOException {
//        System.out.println(NLPTokenizer.segment(""));
//// 注意观察下面两个“希望”的词性、两个“晚霞”的词性
//        System.out.println(NLPTokenizer.analyze("你是？").translateLabels());
//        System.out.println(NLPTokenizer.analyze("支援臺灣正體香港繁體：微软公司於1975年由比爾·蓋茲和保羅·艾倫創立。"));
        CRFLexicalAnalyzer analyzer = new CRFLexicalAnalyzer();
        System.out.println(analyzer.analyze("你是？"));
    }

}
