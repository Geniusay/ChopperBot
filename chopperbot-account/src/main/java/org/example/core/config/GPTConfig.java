package org.example.core.config;

/**
 * @Date 2023/10/13
 * @Author xiaochun
 */
public class GPTConfig {
    public static final String GPT_API_URL = "https://api.misakanetwork.com.cn/v1/chat/completions";

    public static final String GPT_MODEL = "gpt-3.5-turbo-16k-0613";

    public static final String GPT_SYSTEM_BASE = "请你作为一个专门看直播的观众，对下列的观众发送的弹幕内容进行分析，然后根据弹幕内容返回从以下几个标签返回给我合适的标签来形容这段内容";
}
