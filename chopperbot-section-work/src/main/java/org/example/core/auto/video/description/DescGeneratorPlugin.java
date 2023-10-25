package org.example.core.auto.video.description;

import org.example.core.auto.AbstractGeneratePlugin;
import org.example.core.auto.video.title.GptTitleGenerator;
import org.example.util.StringUtil;
import org.springframework.stereotype.Component;

/**
 * @author Genius
 * @date 2023/10/25 21:16
 **/
@Component
public class DescGeneratorPlugin extends AbstractGeneratePlugin<DescGenerator> {

    @Override
    public boolean init() {
        this.type = StringUtil.lowerCaseFirstLetter(GptDescGenerator.class.getName());
        return super.init();
    }
}
