package org.example.exception.plugin;

import org.example.exception.Impl.ResultCode;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author Genius
 * @date 2023/08/02 16:59
 **/
public class PluginNotRegisterException extends PluginException{

    private String pluginName[];
    public PluginNotRegisterException() {
        super("Error! Plugin Not Register!");
        resultCode = ResultCode.PLUGIN_NOT_REGISTER;
    }

    public PluginNotRegisterException(String...pluginName) {
        super(String.format("Error! %s Plugin Not Register!",Arrays.stream(pluginName).collect(Collectors.toList()).toString()));
        resultCode = ResultCode.PLUGIN_NOT_REGISTER;
        this.pluginName = pluginName;
    }
}
