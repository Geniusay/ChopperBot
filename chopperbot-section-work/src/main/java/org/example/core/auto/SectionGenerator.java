package org.example.core.auto;

import org.example.bean.section.PackageSection;
import org.example.bean.section.VideoSection;
import org.example.sql.SQLInitMachine;
import org.example.util.ClassUtil;
import org.springframework.beans.BeanUtils;

import java.util.Map;

/**
 * @author Genius
 * @date 2023/10/25 20:42
 **/
public abstract class SectionGenerator<R> implements AutoGenerator<R>,SQLInitMachine {
    public abstract  <T extends VideoSection,V extends VideoSection> V generator(T section);
}
