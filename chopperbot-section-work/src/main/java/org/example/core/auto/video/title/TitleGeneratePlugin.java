package org.example.core.auto.video.title;

import org.example.core.auto.AutoGenerator;
import org.example.plugin.SpringBootPlugin;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Genius
 * @date 2023/10/22 17:53
 **/
@Component
public class TitleGeneratePlugin extends SpringBootPlugin {

    private TitleGenerator autoGenerator;

    @Override
    public boolean init() {
        return super.init();
    }

    public String generate(Object data) {
        autoGenerator.preGenerate();
        return autoGenerator.generate(data);
    }

}
