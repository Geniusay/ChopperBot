package org.example.log.notice;

public interface NoticeHorn {

    void info(String msg,boolean isNotice);

    void error(String msg,boolean isNotice);

    void warn(String msg,boolean isNotice);

    void info(String title,String msg,boolean isNotice);

    void error(String title,String msg,boolean isNotice);

    void warn(String title,String msg,boolean isNotice);
}
