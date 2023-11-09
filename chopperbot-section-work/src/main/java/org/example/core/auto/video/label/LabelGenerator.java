package org.example.core.auto.video.label;

import org.example.bean.section.PackageSection;
import org.example.bean.section.VideoSection;
import org.example.core.auto.SectionGenerator;
import org.example.mapper.AnalysisSchemeMapper;
import org.example.pojo.AnalysisScheme;
import org.example.sql.annotation.SQLInit;
import org.example.util.ClassUtil;
import org.springframework.beans.BeanUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

public abstract class LabelGenerator extends SectionGenerator<List<String>> {


    @Override
    public <T extends VideoSection, V extends VideoSection> V generator(T section) {
        PackageSection packageSection = new PackageSection();
        BeanUtils.copyProperties(section,packageSection);
        try {
            Map<String, Object> map = ClassUtil.toDeepMap(packageSection);
            List<String> labels = this.generate(map);
            packageSection.setLabels(labels);
        }catch (Exception e){
            return (V) section;
        }
        return (V) packageSection;
    }
}
