package org.example.pojo;

/**
 * 视频稿件实体类
 * @author dhx
 * @date 2023/9/24 20:48
 */
public class VideoManuscript {
    //视频稿件标题
    protected String title;

    //视频封面地址
    protected String coverUrl;

    //简介
    protected String desc;

    public VideoManuscript(String title, String coverUrl, String desc) {
        this.title = title;
        this.coverUrl = coverUrl;
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
