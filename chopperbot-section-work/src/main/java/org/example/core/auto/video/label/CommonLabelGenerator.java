package org.example.core.auto.video.label;

import org.example.util.MapUtil;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class CommonLabelGenerator extends LabelGenerator{
    @Override
    public List<?> sqlInit() {
        return null;
    }

    @Override
    public void preGenerate() {

    }

    @Override
    public List<String> generate(Map<String, Object> data) {
        String liver = MapUtil.getString(data,"liver");
        String platform = MapUtil.getString(data,"platform");
        String tag = MapUtil.getString(data,"tag");
        return List.of(liver,platform,tag);
    }
}
