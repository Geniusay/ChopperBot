package org.example.core.auto.video.description;

import org.example.core.auto.AbstractGeneratePlugin;
import org.example.core.auto.video.title.GptTitleGenerator;
import org.example.util.StringUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author Genius
 * @date 2023/10/25 21:16
 **/
@Component
public class DescGeneratorPlugin extends AbstractGeneratePlugin<DescGenerator> {

    @Resource
    Map<String,DescGenerator> descGeneratorMap;
    @Override
    public boolean init() {
        this.generatorMap = descGeneratorMap;
        this.type = StringUtil.lowerCaseFirstLetter(GptDescGenerator.class.getSimpleName());
        return super.init();
    }
}
