package org.example.core.auto.video.title;

import org.example.bean.section.PackageSection;
import org.example.bean.section.VideoSection;
import org.example.core.auto.AutoGenerator;
import org.example.core.auto.SectionGenerator;
import org.example.sql.SQLInitMachine;
import org.example.sql.annotation.SQLInit;
import org.example.util.ClassUtil;
import org.springframework.beans.BeanUtils;

import java.util.Map;

public abstract class TitleGenerator extends SectionGenerator<String> {
    @Override
    public <T extends VideoSection, V extends VideoSection> V generator(T section) {
        PackageSection packageSection = new PackageSection();
        BeanUtils.copyProperties(section,packageSection);
        try {
            Map<String, Object> map = ClassUtil.toDeepMap(packageSection);
            String title = this.generate(map);
            packageSection.setTitle(title);
        }catch (Exception e){
            return (V) section;
        }
        return (V) packageSection;
    }
}

