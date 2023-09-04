package org.example.log;


/**
 * 启动日志接口
 */
public interface ResultLogger {

    void successLog();

    void successLog(String str);

    void failLog();

    void failLog(String str);
}
