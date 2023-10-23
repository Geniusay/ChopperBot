package org.example.account;

import org.example.ConsoleApplication;
import org.example.constpool.PluginName;
import org.example.core.gpt.ChatGPTMsgBuilder;
import org.example.core.gpt.OpenAPIPlugin;
import org.example.init.InitPluginRegister;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Date 2023/10/14
 * @Author xiaochun
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ConsoleApplication.class,webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class gptTest {

    @Resource
    OpenAPIPlugin plugin;
    //        String videoInfo = "弹幕：帅,这操作我要学一年,太6了";
//        String systemInfo = "请你作为一个专门看直播的观众，对下列的观众发送的弹幕内容进行分析，然后根据弹幕内容返回从以下几个标签返回给我最合适的一个标签来形容这段内容标签：[搞笑,秀操作,破防,泪目]";
    @Test
    public void test(){
        ChatGPTMsgBuilder builder = new ChatGPTMsgBuilder().model(plugin.choseKey(OpenAPIPlugin.APIFunc.CHAT_GPT).getModel())
                .system("请你作为一个专门看直播的观众，对下列的观众发送的弹幕内容进行分析，然后根据弹幕内容返回从以下几个标签返回给我最合适的一个标签来形容这段内容标签：[搞笑,秀操作,破防,泪目]")
                .user("弹幕：帅,这操作我要学一年,太6了")
                .stream(false);
        System.out.println(builder.done());
        OpenAPIPlugin plugin1 = InitPluginRegister.getPlugin(PluginName.CHAT_GPT, OpenAPIPlugin.class);
        System.out.println(plugin.reqGPT(builder, OpenAPIPlugin.APIFunc.CHAT_GPT));
        System.out.println(plugin1.reqGPT(builder, OpenAPIPlugin.APIFunc.CHAT_GPT));
    }
}
