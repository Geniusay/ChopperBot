package org.example.core.auto.video.label;

import org.example.core.auto.AbstractGeneratePlugin;
import org.example.core.auto.video.title.TitleGenerator;
import org.example.util.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;


@Component
public class LabelGeneratePlugin extends AbstractGeneratePlugin<LabelGenerator> {

    @Resource
    Map<String, LabelGenerator> labelGeneratorMap;

    @Value("${chopperbot.plugin.label-generate-plugin.handler}")
    private String configType;
    @Override
    public boolean init() {
        this.generatorMap = labelGeneratorMap;
        this.type = StringUtil.lowerCaseFirstLetter(configType);
        return super.init();
    }

}
