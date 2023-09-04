package org.example.pojo;
/**
 * @description : [配置文件实体类]
 * @author : [Welsir]
 * @createTime : [2023/5/15 22:51]
 */

import java.util.List;
import java.util.Map;

public class Anchor {

    private String plathForm;

    private String roomId;

    private String nickName;

    private Map<String,Integer> keywordScore;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPlathForm() {
        return plathForm;
    }

    public void setPlathForm(String plathForm) {
        this.plathForm = plathForm;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public Map<String, Integer> getKeywordScore() {
        return keywordScore;
    }

    public void setKeywordScore(Map<String, Integer> keywordScore) {
        this.keywordScore = keywordScore;
    }

    @Override
    public String toString() {
        return "{name:"+getNickName()+",KeywordScore:"+getKeywordScore()+"}";
    }
}
