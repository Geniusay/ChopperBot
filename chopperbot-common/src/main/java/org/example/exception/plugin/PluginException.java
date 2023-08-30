package org.example.exception.plugin;

import org.example.exception.ChopperBotException;

/**
 * @author Genius
 * @date 2023/08/02 16:50
 **/
public class PluginException extends ChopperBotException {

    public PluginException(String message) {
        super(message);
    }



    @Override
    public String getMessage() {
        return super.getMessage();
    }

}
