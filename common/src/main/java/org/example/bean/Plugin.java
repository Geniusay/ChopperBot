package org.example.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.init.InitMachine;

import java.util.List;

/**
 * @author Genius
 * @date 2023/07/31 20:14
 **/
@Data
@AllArgsConstructor
public class Plugin{
    private InitMachine initMachine;
    private String moduleName;
    private String pluginName;
    private List<String> needPlugin;
    private boolean enable;
}
