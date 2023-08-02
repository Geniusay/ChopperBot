package org.example.exception;

import org.example.log.ChopperLogFactory;
import org.example.log.LoggerType;
import org.slf4j.Logger;

/**
 * @author Genius
 * @date 2023/08/02 16:52
 **/
public class ChopperBotException extends BaseException{



    protected Logger logger = ChopperLogFactory.getLogger(LoggerType.System);

    public ChopperBotException(String message) {
        super(message);
    }
}
