package org.example.core.auto;

import org.example.bean.section.PackageSection;
import org.example.bean.section.VideoSection;

public interface SectionPipeline {
    <O extends VideoSection,I extends VideoSection> O process(I section);
}
