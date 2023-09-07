package org.example.core.manager;

import com.google.common.reflect.TypeToken;
import org.example.core.loadconfig.LoadConfig;
import org.example.core.loadtask.LoadTask;
import org.springframework.stereotype.Component;

/**
 * @author Genius
 * @date 2023/09/05 18:33
 **/
@Component
public abstract class CommonLoadConfigBuilder<T extends LoadConfig> implements LoadConfigBuilder<T> {

    private Class<T> type;

    public CommonLoadConfigBuilder() {
        this.type = (Class<T>) new TypeToken<T>(getClass()) {}.getType();
    }

    @Override
    public Class<? extends LoadConfig> getLoadConfigClass() {
        return type;
    }
}
