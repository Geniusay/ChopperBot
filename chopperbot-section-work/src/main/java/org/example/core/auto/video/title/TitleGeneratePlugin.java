package org.example.core.auto.video.title;

import org.example.bean.section.PackageSection;
import org.example.bean.section.VideoSection;
import org.example.core.auto.AbstractGeneratePlugin;
import org.example.core.auto.SectionPipeline;
import org.example.plugin.SpringBootPlugin;
import org.example.util.StringUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Genius
 * @date 2023/10/22 17:53
 **/
@Component
public class TitleGeneratePlugin extends AbstractGeneratePlugin<TitleGenerator> {
    @Override
    public boolean init() {
        this.type = StringUtil.lowerCaseFirstLetter(GptTitleGenerator.class.getSimpleName());
        return super.init();
    }


}
