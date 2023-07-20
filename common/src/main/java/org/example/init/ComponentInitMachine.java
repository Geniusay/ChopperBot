package org.example.init;

/**
 * @author Genius
 * @date 2023/07/21 00:12
 **/

public interface ComponentInitMachine extends InitMachine{

    default boolean fail(){
        return fail("");
    }

    boolean fail(String failCause);

    boolean success();
}
