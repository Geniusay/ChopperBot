package org.example.core.manager;

import org.example.core.loadconfig.LoadConfig;
import org.example.core.loadtask.LoadTask;

/**
 * @author Genius
 * @date 2023/09/05 18:23
 **/
public interface LoadConfigBuilder<T extends LoadConfig> {

     String getName();
     T build(Object obj);
}
