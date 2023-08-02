package org.example.pojo;

/**
 * @Description Barrage scoring entity class
 * @Author Welsir
 * @Date 2023/5/29 20:20
 */
public class BarrageScore {

    private int scoreValue;
    private int contentValue;
    private String content;

    public BarrageScore(){

    }
    public BarrageScore(int score){
        this.scoreValue = score;
    }

    public int getScoreValue() {
        return scoreValue;
    }

    public void setScoreValue(int scoreValue) {
        this.scoreValue = scoreValue;
    }

    public int getContentValue() {
        return contentValue;
    }

    public void setContentValue(int contentValue) {
        this.contentValue = contentValue;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
