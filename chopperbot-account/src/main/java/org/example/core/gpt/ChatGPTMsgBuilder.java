package org.example.core.gpt;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.core.msgbuilder.AbstractMsgBuilder;

import java.util.List;

/**
 * @Date 2023/10/13
 * @Author xiaochun
 */
public class ChatGPTMsgBuilder extends AbstractMsgBuilder {

   private Role system = new Role("system","");
   private Role user = new Role("user","");
   public ChatGPTMsgBuilder system(String msg){
       system.setContent(msg);
       return this;
   }
    public ChatGPTMsgBuilder user(String msg){
        user.setContent(msg);
        return this;
    }

    public ChatGPTMsgBuilder model(String msg){
       build("model",msg);
        return this;
    }

    public ChatGPTMsgBuilder stream(boolean b){
       build("stream",b);
        return this;
    }

    @Override
    public String done() {
        this.map.put("messages", List.of(system,user));
        return super.done();
    }

    @Data
    @AllArgsConstructor
    static class Role{
        private String role;
        private String content;
    }
}
