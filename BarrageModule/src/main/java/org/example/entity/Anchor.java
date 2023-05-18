package org.example.entity;
/**
 * @description : [配置文件实体类]
 * @author : [Welsir]
 * @createTime : [2023/5/15 22:51]
 */

import java.util.List;

/**
 * @author welsir
 * @date 2023/5/15 22:51
 */
public class Anchor {
    private String name;

    public List<Anchor.property> getProperty() {
        return property;
    }

    public void setProperty(List<Anchor.property> property) {
        this.property = property;
    }

    private List<property> property;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    @Override
    public String toString() {
        return "{name:"+getName()+",property:"+getProperty()+"}";
    }
}
