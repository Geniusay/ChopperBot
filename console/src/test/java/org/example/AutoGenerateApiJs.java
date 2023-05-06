package org.example;

import com.genius.assistant.warmup.generate.AutoApiJsGenerate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author Genius
 * @date 2023/04/26 01:13
 **/
@SpringBootTest
public class AutoGenerateApiJs {

    @Resource
    AutoApiJsGenerate autoApiJsGenerate;

    @Test
    public void autoGenerateApiJs(){
        autoApiJsGenerate.setIsJsMoodleGenerated(true).setFileSavePath("E:\\Project\\ChopperBot\\console\\src\\main\\resources\\js");
        autoApiJsGenerate.setAxiosPath("@/utils/request.ts");
        autoApiJsGenerate.generate();
    }
}
