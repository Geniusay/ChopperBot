package org.example.core.auto.video.description;

import org.example.bean.section.PackageSection;
import org.example.bean.section.VideoSection;
import org.example.core.auto.SectionGenerator;
import org.example.util.ClassUtil;
import org.springframework.beans.BeanUtils;

import java.util.Map;

/**
 * @author Genius
 * @date 2023/10/25 20:38
 **/
public abstract class DescGenerator extends SectionGenerator<String> {
    @Override
    public <T extends VideoSection, V extends VideoSection> V generator(T section) {
        PackageSection packageSection = new PackageSection();
        BeanUtils.copyProperties(section,packageSection);
        try {
            Map<String, Object> map = ClassUtil.toDeepMap(packageSection);
            String desc = this.generate(map);
            packageSection.setDescription(desc);
        }catch (Exception e){
            return (V) section;
        }
        return (V) packageSection;
    }
}
