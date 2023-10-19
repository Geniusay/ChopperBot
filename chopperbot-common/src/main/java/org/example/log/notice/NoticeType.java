package org.example.log.notice;

/**
 * @author Genius
 * @date 2023/10/19 15:25
 **/
public enum NoticeType {
    INFO("info"),
    WARN("warn"),
    ERROR("error");
    private final String type;

    NoticeType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
