package org.example.pojo;
/**
 * @description : [配置文件实体类]
 * @author : [Welsir]
 * @createTime : [2023/5/15 22:51]
 */

import java.util.List;
public class Anchor {

    private String plathForm;

    private String roomId;

    private String nickName;

    private List<property> property;

    public static class property{
        int weight;

        String content;

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        @Override
        public String toString() {
            return "{weight:"+getWeight()+",content:"+getContent()+"}";
        }

        public property(String content,int weight){
            this.content = content;
            this.weight = weight;
        }
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setProperty(List<Anchor.property> property) {
        this.property = property;
    }

    public List<Anchor.property> getProperty() {
        return property;
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

    @Override
    public String toString() {
        return "{name:"+getNickName()+",property:"+getProperty()+"}";
    }
}
