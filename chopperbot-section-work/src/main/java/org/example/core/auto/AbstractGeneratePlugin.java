package org.example.core.auto;

import org.example.bean.section.VideoSection;
import org.example.plugin.SpringBootPlugin;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Genius
 * @date 2023/10/25 20:41
 **/
public abstract class AbstractGeneratePlugin<G extends SectionGenerator<?>> extends SpringBootPlugin implements SectionPipeline{
    @Resource
    protected Map<String, G> generatorMap;

    private G generator;

    protected String type;

    @Override
    public boolean init() {
        if(!StringUtils.hasText(type)){
            generatorMap.forEach((k,v)->{
                this.type = k;
                this.generator = v;
            });
        }else{
            this.generator = generatorMap.get(type);
        }
        if(generator==null){
            throw new RuntimeException("Invalid generator type");
        }
        generator.sqlInit();
        return super.init();
    }

    @Override
    public <T extends VideoSection, V extends VideoSection> T process(V section) {
        generator.preGenerate();
        return generator.generator(section);
    }

    public boolean changeType(String type){
        if(generatorMap.containsKey(this.type)){
            this.generator = generatorMap.get(this.type);
            generator.sqlInit();
            this.type = type;
            return true;
        }
        return false;
    }

    public List<String> types(){
        return new ArrayList<>(generatorMap.keySet());
    }
}
