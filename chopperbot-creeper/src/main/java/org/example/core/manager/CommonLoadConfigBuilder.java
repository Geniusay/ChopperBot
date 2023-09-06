package org.example.core.manager;

import org.example.core.loadconfig.LoadConfig;
import org.example.core.loadtask.LoadTask;
import org.springframework.stereotype.Component;

/**
 * @author Genius
 * @date 2023/09/05 18:33
 **/
@Component
public abstract class CommonLoadConfigBuilder<T extends LoadConfig> implements LoadConfigBuilder<T> {

}
