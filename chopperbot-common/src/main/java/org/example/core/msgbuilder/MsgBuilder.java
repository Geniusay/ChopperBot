package org.example.core.msgbuilder;

/**
 * @Date 2023/10/13
 * @Author xiaochun
 */
public interface MsgBuilder {
    MsgBuilder build(String key,Object data);

    String done();
}
