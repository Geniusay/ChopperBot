package org.example.log.notice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.util.TimeUtil;

import java.time.LocalDateTime;

/**
 * @author Genius
 * @date 2023/10/19 15:29
 **/
@Data
@NoArgsConstructor
public class Notice {
    private String type;
    private String from;
    private String content;

    private String title = "ChopperBot";

    private String time = TimeUtil.getNowTime_YMDHMS();

    public Notice(String type, String from, String content) {
        this.type = type;
        this.from = from;
        this.content = content;
    }

    public Notice(NoticeType type, String from, String content) {
        this.type = type.getType();
        this.from = from;
        this.content = content;
    }

    public void setType(NoticeType type) {
        this.type = type.getType();
    }
    public Notice warn(){
        this.type = NoticeType.WARN.getType();
        return this;
    }

    public Notice info(){
        this.type = NoticeType.INFO.getType();
        return this;
    }
    public Notice error(){
        this.type = NoticeType.ERROR.getType();
        return this;
    }

    public Notice from(String from){
        this.from = from;
        return this;
    }

    public Notice content(String content){
        this.content = content;
        return this;
    }

    public Notice title(String title){
        this.title = title;
        return this;
    }
}
