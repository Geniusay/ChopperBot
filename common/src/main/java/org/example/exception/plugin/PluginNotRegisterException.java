package org.example.exception.plugin;

import org.example.exception.Impl.ResultCode;

/**
 * @author Genius
 * @date 2023/08/02 16:59
 **/
public class PluginNotRegisterException extends PluginException{

    public PluginNotRegisterException() {
        super("Error! Plugin Not Register!");
        resultCode = ResultCode.PLUGIN_NOT_REGISTER;
    }
}
