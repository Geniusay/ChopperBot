package org.example.core.manager;

import org.example.core.loadconfig.LoadConfig;
import org.example.core.loadtask.LoadTask;

/**
 * @author Genius
 * @date 2023/09/05 18:23
 **/
public interface LoadConfigBuilder<T extends LoadConfig> {

     Class<? extends LoadConfig> getLoadConfigClass();
     T build(Object obj);
}
