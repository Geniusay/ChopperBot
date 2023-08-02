package org.example.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author Genius
 * @date 2023/08/02 18:17
 **/
@Data
@AllArgsConstructor
public class Plugin {
    private String pluginName;
    private String pluginModule;
    private List<String> needPlugins;
    private boolean isStart;
    private boolean isRegister;
}
